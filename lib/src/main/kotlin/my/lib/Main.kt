package my.lib
import java.util.*

class EulerTourTree(var size: Int) {

    private var edges: Array<HashMap<Int, node>?>
    private var backEdges: Array<HashMap<Int, node>?>
    private var nodes: Array<HashSet<node>?>

    init {
        nodes = Array(size + 1, { null })
        edges = Array(size + 1, { null })
        backEdges = Array(size + 1, { null })

        for (i in 1..size + 1 - 1) {
            nodes[i] = HashSet()
            val newNode = node(i)
            nodes[i]!!.add(newNode)
            edges[i] = HashMap()
            edges[i]!!.put(i, newNode)
            backEdges[i] = HashMap()
            backEdges[i]!!.put(i, newNode)
        }
    }

    fun link(left: Int, right: Int) {
        if (connected(left, right)) {
            return
        }
        val leftNode = nodes[left]!!.iterator().next()
        val rightNode = nodes[right]!!.iterator().next()
        splay(leftNode)
        var A2 = leftNode.rightSon
        leftNode.rightSon = null
        refreshWeights(leftNode)
        if (A2 != null) {
            A2.parent = null
            A2 = addToTheLeft(A2, leftNode.value)
        } else {
            val newNode = node(leftNode.value)
            nodes[leftNode.value]!!.add(newNode)
            A2 = newNode
        }

        splay(rightNode)
        var B1 = rightNode.leftSon
        rightNode.leftSon = null
        refreshWeights(rightNode)
        if (B1 != null) {
            B1.parent = null
            B1 = addToTheRight(B1, rightNode.value)
            B1 = removeFirst(B1)
        }
        merge(leftNode, rightNode)
        merge(rightNode, B1)
        merge(rightNode, A2)
    }

    fun cut(left: Int, right: Int) {
        if (!connected(left, right)) {
            return
        }
        var left = left
        var right = right

        val supposedLeft = edges[left]!![right]
        val supposedRight = backEdges[right]!![left]
        if (supposedLeft === supposedRight) {
            val temp = left
            left = right
            right = temp
        } else {
            splay(supposedLeft)
            splayUntilSonOfRoot(supposedRight, supposedLeft)
            if (supposedLeft!!.leftSon === supposedRight) {
                val temp = left
                left = right
                right = temp
            }
        }

        var leftNodeA = edges[left]!![right]
        var rightNodeA = backEdges[right]!![left]

        var leftNodeB = backEdges[left]!![right]
        var rightNodeB = edges[right]!![left]

        splay(leftNodeA)
        splayUntilSonOfRoot(leftNodeB, leftNodeA)
        leftNodeA!!.rightSon = null
        refreshWeights(leftNodeA)
        leftNodeB!!.parent = null

        splay(rightNodeB)
        splayUntilSonOfRoot(rightNodeA, rightNodeB)
        rightNodeB!!.rightSon = null
        refreshWeights(rightNodeB)
        rightNodeA!!.parent = null

        rightNodeA = removeFirst(rightNodeA)
        merge(leftNodeA, rightNodeA)
    }

    fun connected(left: Int, right: Int): Boolean {
        var first: node? = nodes[left]!!.iterator().next()
        var second: node? = nodes[right]!!.iterator().next()
        while (first!!.parent != null) {
            first = first.parent
        }
        while (second!!.parent != null) {
            second = second.parent
        }
        return first === second
    }

    fun sizeOfNodesComponent(index: Int): Int {
        var node: node = nodes[index]!!.iterator().next()
        splay(node)
        return (node.weight + 1) / 2
    }

    private class node(var value: Int) {
        var parent: node? = null
        var rightSon: node? = null
        var leftSon: node? = null
        var weight: Int = 0

        init {
            var value: Int = value
            weight = 1
        }
    }

    private fun zigUntilSonOfRoot(node: node, isLeftSon: Boolean) {
        val parent = node.parent
        val grandparent = parent!!.parent
        var secondLeft = false
        if (grandparent!!.leftSon === parent) {
            secondLeft = true
        }
        zig(node, isLeftSon)
        node.parent = grandparent
        if (secondLeft) {
            grandparent!!.leftSon = node
        } else {
            grandparent!!.rightSon = node
        }
    }

    private fun zig(node: node, isLeftSon: Boolean) {
        val parent = node.parent
        if (isLeftSon) {
            val b = node.rightSon

            parent!!.leftSon = b
            if (b != null) {
                b.parent = parent
            }

            node.parent = null
            node.rightSon = parent
            parent.parent = node
        } else {
            val b = node.leftSon

            parent!!.rightSon = b
            if (b != null) {
                b.parent = parent
            }

            node.parent = null
            node.leftSon = parent
            parent.parent = node
        }
        refreshWeights(parent)
    }

    private fun zigZig(node: node, isLeftSon: Boolean) {
        val parent = node.parent
        val grandparent = parent!!.parent
        val grandGrandparent = grandparent!!.parent
        if (isLeftSon) {
            val b = node.rightSon
            val c = parent.rightSon

            grandparent.parent = parent
            grandparent.leftSon = c
            if (c != null) {
                c.parent = grandparent
            }

            parent.leftSon = b
            parent.rightSon = grandparent
            if (b != null) {
                b.parent = parent
            }

            node.rightSon = parent
            node.parent = grandGrandparent
            parent.parent = node
        } else {
            val b = parent.leftSon
            val c = node.leftSon

            grandparent.parent = parent
            grandparent.rightSon = b
            if (b != null) {
                b.parent = grandparent
            }

            parent.rightSon = c
            parent.leftSon = grandparent
            if (c != null) {
                c.parent = parent
            }

            node.leftSon = parent
            node.parent = grandGrandparent
            parent.parent = node
        }
        if (grandGrandparent != null) {
            if (grandGrandparent.leftSon === grandparent) {
                grandGrandparent.leftSon = node
            } else {
                grandGrandparent.rightSon = node
            }
        }
        refreshWeights(grandparent)
    }

    private fun zigZag(node: node, isLeftSon: Boolean) {
        val parent = node.parent
        val grandparent = parent!!.parent
        val grandGrandparent = grandparent!!.parent
        if (isLeftSon) {
            val b = node.leftSon
            val c = node.rightSon

            grandparent.parent = node
            grandparent.rightSon = b
            if (b != null) {
                b.parent = grandparent
            }

            parent.parent = node
            parent.leftSon = c
            if (c != null) {
                c.parent = parent
            }

            node.rightSon = parent
            node.leftSon = grandparent
            node.parent = grandGrandparent
        } else {
            val b = node.leftSon
            val c = node.rightSon

            grandparent.parent = node
            grandparent.leftSon = c
            if (c != null) {
                c.parent = grandparent
            }

            parent.parent = node
            parent.rightSon = b
            if (b != null) {
                b.parent = parent
            }

            node.leftSon = parent
            node.rightSon = grandparent
            node.parent = grandGrandparent
        }
        if (grandGrandparent != null) {
            if (grandGrandparent.leftSon === grandparent) {
                grandGrandparent.leftSon = node
            } else {
                grandGrandparent.rightSon = node
            }
        }
        parent.weight = 1
        if (parent.rightSon != null) {
            parent.weight += parent.rightSon!!.weight
        }
        if (parent.leftSon != null) {
            parent.weight += parent.leftSon!!.weight
        }
        refreshWeights(grandparent)
    }

    private fun splay(node: node?) {
        while (node!!.parent != null) {
            val parent = node.parent
            var isLeftSon = false
            if (parent!!.leftSon === node) {
                isLeftSon = true
            }
            if (parent!!.parent == null) {
                zig(node, isLeftSon)
            } else {
                var secondLeft = false
                val grandparent = parent.parent
                if (grandparent!!.leftSon === parent) {
                    secondLeft = true
                }
                if (isLeftSon && secondLeft) {
                    zigZig(node, true)
                } else if (isLeftSon && !secondLeft) {
                    zigZag(node, true)
                } else if (!isLeftSon && secondLeft) {
                    zigZag(node, false)
                } else {
                    zigZig(node, false)
                }
            }
        }
    }

    private fun addToTheLeft(node: node?, value: Int): node {
        if (node == null) {
            val newNode = node(value)
            nodes[value]!!.add(newNode)
            return newNode
        }
        val firstNode = findFirst(node)
        val newNode = node(value)
        nodes[value]!!.add(newNode)
        firstNode!!.leftSon = newNode
        refreshWeights(firstNode)
        newNode.parent = firstNode
        edges[value]!!.put(firstNode.value, newNode)
        backEdges[value]!!.put(firstNode.value, firstNode)
        return node
    }

    private fun addToTheRight(node: node?, value: Int): node {
        if (node == null) {
            val newNode = node(value)
            nodes[value]!!.add(newNode)
            return newNode
        }
        val lastNode = findLast(node)
        val newNode = node(value)
        nodes[value]!!.add(newNode)
        lastNode!!.rightSon = newNode
        refreshWeights(lastNode)
        newNode.parent = lastNode
        edges[lastNode.value]!!.put(newNode.value, lastNode)
        backEdges[lastNode.value]!!.put(newNode.value, newNode)
        return node
    }

    private fun removeFirst(node: node?): node? {
        val first = findFirst(node)
        nodes[first!!.value]!!.remove(first)

        val parent = first.parent
        if (parent == null) {
            val returnNode = first.rightSon ?: return null
            returnNode.parent = null
            return returnNode
        } else {
            parent.leftSon = first.rightSon
            if (first.rightSon != null) {
                first.rightSon!!.parent = parent
            }
            refreshWeights(parent)
            return parent
        }
    }

    private fun findFirst(node: node?): node? {
        var node: node? = node ?: return null
        while (node!!.parent != null) {
            node = node.parent
        }
        while (node!!.leftSon != null) {
            node = node.leftSon
        }
        return node
    }

    private fun findLast(node: node?): node? {
        var node: node? = node ?: return null
        while (node!!.parent != null) {
            node = node.parent
        }
        while (node!!.rightSon != null) {
            node = node.rightSon
        }
        return node
    }

    private fun merge(left: node?, right: node?) {
        if (left == null || right == null) {
            return
        }
        val lastLeft = findLast(left)
        val firstRight = findFirst(right)
        splay(lastLeft)
        splay(right)
        lastLeft!!.rightSon = right
        refreshWeights(lastLeft)
        right.parent = lastLeft
        edges[lastLeft.value]!!.put(firstRight!!.value, lastLeft)
        backEdges[lastLeft.value]!!.put(firstRight.value, firstRight)
    }

    private fun splayUntilSonOfRoot(node: node?, neededParent: node?) {
        while (node!!.parent !== neededParent && node!!.parent != null) {
            val parent = node.parent
            var isLeftSon = false
            if (parent!!.leftSon === node) {
                isLeftSon = true
            }
            if (parent!!.parent === neededParent) {
                zigUntilSonOfRoot(node, isLeftSon)
            } else {
                var secondLeft = false
                val grandparent = parent!!.parent
                if (grandparent!!.leftSon === parent) {
                    secondLeft = true
                }
                if (isLeftSon && secondLeft) {
                    zigZig(node, true)
                } else if (isLeftSon && !secondLeft) {
                    zigZag(node, true)
                } else if (!isLeftSon && secondLeft) {
                    zigZag(node, false)
                } else {
                    zigZig(node, false)
                }
            }
        }
    }

    private fun refreshWeights(node: node?) {
        var node = node
        while (true) {
            if (node == null) {
                break
            }
            val leftSon = node.leftSon
            val rightSon = node.rightSon
            node.weight = 1
            if (leftSon != null) {
                node.weight += leftSon.weight
            }
            if (rightSon != null) {
                node.weight += rightSon.weight
            }
            node = node.parent
        }
    }
}