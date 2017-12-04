import graph.*
import graph.algorithms.findEulerPath
import graph.algorithms.findMatching
import graph.algorithms.findMinPathCoverageInNonOriented
import graph.algorithms.findMinPathCoverageInOriented
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.BufferedReader
import java.io.File
import java.util.*

class MainTest {

    companion object {

        const val TESTS_PATH = "src/test/kotlin/testData/"
        const val MATCHING_TESTS_PATH = "$TESTS_PATH/matching/"
        const val EULER_TESTS_PATH = "$TESTS_PATH/euler/"
        const val ORIENTED_TESTS_PATH = "$TESTS_PATH/oriented/"
        const val NON_ORIENTED_TESTS_PATH = "$TESTS_PATH/non_oriented/"

        val matchingFileNames = (1..4).map { "$MATCHING_TESTS_PATH$it.txt" }
        val eulerFileNames = (1..2).map { "$EULER_TESTS_PATH$it.txt" }
        val pathCoverageOrientedFileNames = (1..2).map { "$ORIENTED_TESTS_PATH$it.txt" }
        val pathCoverageNonOrientedFileNames = (1..10).map { "$NON_ORIENTED_TESTS_PATH$it.txt" }

    }

    private val random = Random()

    open class TestSet<GraphT : AbstractGraph, T>(
            val graph: GraphT,
            val algorithm: (GraphT) -> T,
            val checker: (T) -> Boolean) {
        fun check() = checker(algorithm(graph))
    }

    class PathCoverageTestSet(graph: Graph, size: Int): TestSet<Graph, List<Path>>(graph, {
        if (it.isNonOriented) it.findMinPathCoverageInNonOriented()
        else it.findMinPathCoverageInOriented()
    }, { coverage ->
        println("coverage: ${coverage.map { it.edges.map{"(" + (it.from + 1) + "," + (it.to + 1) + ")"}.joinToString(", ") }.joinToString("\n")}")
        coverage.size == size && coverage.all { path ->
            path.edges.filter { it.from != it.to } .all { it.to in graph.g[it.from] }
        }
    })

    class EulerPathTestSet(graph: Graph): TestSet<Graph, Path>(graph, { it.toMultiGraph().findEulerPath()
    }, { eulerPath ->
        val expectedAnswer = (graph.edgeCount) / 2
        eulerPath.edges.size >= expectedAnswer && eulerPath.edges.all {
            (it.to in graph.g[it.from])
        }
    })

    class MatchingTestSet(graph: BipartieGraph, size: Int): TestSet<BipartieGraph, List<Int?>>(
            graph,
            BipartieGraph::findMatching,
            { it.size == size &&
                    it.mapIndexed { r, l -> l?.let { Edge(it, r) } }
                    .filterNotNull()
                    .all { it.to in graph.g[it.from] }
            }
    )

    private fun BufferedReader.readInts() = this.readLine().split(' ').map(String::toInt)

    private fun BufferedReader.readGraph() : Graph {
        val (n, m) = this.readInts()
        return Graph.create(n, m, Array(m) { this.readInts().let { Edge(it[0], it[1]) } }.toList() )
    }

    private fun BufferedReader.readBipartieGraph(): BipartieGraph {
        val (l, r) = this.readInts()
        return BipartieGraph(l, r, Array(l) { this.readInts().toMutableList() } )
    }

    private fun List<Int>.toPath() = this.let { path ->
        path.drop(1).mapIndexed { i, v ->
            Edge(path[i - 1], v)
        }
    }

    private fun BufferedReader.readPath() = Path(this.readInts().toPath())

    private fun BufferedReader.readPathSet() = mutableListOf<Path>().also {
        this.lineSequence().forEach {
            it.map { it.toInt() }.toPath()
        }
        it.add(this.readPath())
    }

    private fun File.readPathCoverageTestSet() = this.bufferedReader().let {
        PathCoverageTestSet(
                it.readGraph(),
                it.readInts()[0]
        )
    }

    private fun File.readEulerPathTestSet() =  this.bufferedReader().let { EulerPathTestSet(it.readGraph()) }

    private fun File.readMatchingTestSet() =  this.bufferedReader().let {
        MatchingTestSet(
                it.readBipartieGraph(),
                it.readInts()[0]
        )
    }


    fun <G: AbstractGraph, T> testFromFile(path: String, reader: File.() -> TestSet<G, T>) = File(path).reader().check()
    fun testEulerPathFromFile(path: String) = testFromFile(path) { this.readEulerPathTestSet() }
    fun testPathCoverageFromFile(path: String) = testFromFile(path) { this.readPathCoverageTestSet() }
    fun testMatchingFromFile(path: String) = testFromFile(path) { this.readMatchingTestSet() }

    private infix fun Int.power(deg: Int): Int = (this * this.power(deg - 1)).takeIf { deg != 0 } ?: 1

    fun generateEulerPathTestSet(vertexes: Int) : EulerPathTestSet {
        val g = Array(vertexes) { (0 until vertexes).filter { random.nextBoolean() }.toMutableList() }
        var ok = false
        while (!ok) {
            ok = true
            val additional = arrayListOf<Edge>()
            g.forEachIndexed { v, outcoming ->
                additional.addAll(outcoming.filter { v !in g[it] }.map { Edge(it, v) })
            }
            additional.forEach { (it, v) ->
                g[it].add(v)
            }
            if (additional.isNotEmpty())
                ok = false
            g.forEach {
                if (it.size % 2 == 1) {
                    it.add((0 until vertexes)
                            .toMutableList()
                            .also { rest -> rest.removeAll(it) }[0])
                    ok = false
                }
            }
        }
        if (g.mapIndexed { v, es -> es.count { v in g[it] } }.sum() != 0)
            return EulerPathTestSet(Graph.default)
        val used = Array(vertexes) { false }
        fun dfs(v: Int): Unit {
            if (used[v])
                return
            used[v] = true
            g[v].forEach(::dfs)
        }
        dfs(0)
        return EulerPathTestSet(
                if (used.any { !it }) Graph.default
                else (Graph(vertexes, g.map { it.size }.sum(), g))
        )
    }

    fun <G: AbstractGraph, T>generateAndTest(vertexes: Int, edges: Int, generator: (Int, Int) -> TestSet<G, T>) =
            generator(vertexes, edges).check()

    @Test
    fun testMatchingFromFiles() = assertTrue(matchingFileNames.all { testMatchingFromFile(it) })

    @Test
    fun testEulerFromFiles() = assertTrue(eulerFileNames.all { testEulerPathFromFile(it) })

    @Test
    fun testEulerGens() = assertTrue((2 until 30 step 2).asSequence()
            .map { generateEulerPathTestSet(it) }
            .all { it.check() })

    @Test
    fun testPathCoverageOrientedFromFiles() = assertTrue(pathCoverageOrientedFileNames.all {
        testPathCoverageFromFile(it)
    })

    @Test
    fun testPathCoverageNonOrientedFromFiles() = assertTrue(
            pathCoverageNonOrientedFileNames.all { testPathCoverageFromFile(it) }
    )

    @Test
    fun testPathCoverageFromFiles() {
        testPathCoverageOrientedFromFiles()
        testPathCoverageNonOrientedFromFiles()
    }

    @Test
    fun testAllFromFiles() {
        testMatchingFromFiles()
        testEulerFromFiles()
        testPathCoverageFromFiles()
    }

}