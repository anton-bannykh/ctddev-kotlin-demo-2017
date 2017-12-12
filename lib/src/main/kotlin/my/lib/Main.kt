package my.lib

import java.util.*

class EulerTourTree(var size: Int) {

    private val edges: Array<HashMap<Int, Node>> = Array(size + 1, { HashMap<Int, Node>() })
    private val backEdges: Array<HashMap<Int, Node>> = Array(size + 1, { HashMap<Int, Node>() })
    private val nodes: Array<HashSet<Node>> = Array(size + 1, { HashSet<Node>() })

    init {
        for (i in 1..size) {
            val newNode = Node(i)
            nodes[i].add(newNode)
            edges[i].put(i, newNode)
            backEdges[i].put(i, newNode)
        }
    }

    private class Node(var value: Int) {
        var parent: Node? = null
        var rightSon: Node? = null
        var leftSon: Node? = null
        var weight: Int = 0

        init {
            var value: Int = value
            weight = 1
        }
    }

    fun link(left: Int, right: Int) {
        if (connected(left, right)) {
            return
        }
        val leftNode = nodes[left].iterator().next()
        val rightNode = nodes[right].iterator().next()
        splay(leftNode)
        var a2 = leftNode.rightSon
        leftNode.rightSon = null
        refreshWeights(leftNode)
        if (a2 != null) {
            a2.parent = null
            a2 = addToTheLeft(a2, leftNode.value)
        } else {
            val newNode = Node(leftNode.value)
            nodes[leftNode.value].add(newNode)
            a2 = newNode
        }

        splay(rightNode)
        var b1 = rightNode.leftSon
        rightNode.leftSon = null
        refreshWeights(rightNode)
        if (b1 != null) {
            b1.parent = null
            b1 = addToTheRight(b1, rightNode.value)
            b1 = removeFirst(b1)
        }
        merge(leftNode, rightNode)
        merge(rightNode, b1)
        merge(rightNode, a2)
    }

    fun cut(left: Int, right: Int) {
        if (!directlyConnected(left, right)) {
            return
        }
        var left = left
        var right = right

        val supposedLeft = edges[left][right]
        val supposedRight = backEdges[right][left]
        if (supposedLeft === supposedRight) {
            val temp = left
            left = right
            right = temp
        } else {
            splay(supposedLeft)
            splayUntilSonOfRoot(supposedRight, supposedLeft)
            if (supposedLeft?.leftSon === supposedRight) {
                val temp = left
                left = right
                right = temp
            }
        }

        val leftNodeA = edges[left][right]
        var rightNodeA = backEdges[right][left]

        val leftNodeB = backEdges[left][right]
        val rightNodeB = edges[right][left]

        splay(leftNodeA)
        splayUntilSonOfRoot(leftNodeB, leftNodeA)
        leftNodeA?.rightSon = null
        refreshWeights(leftNodeA)
        leftNodeB?.parent = null

        splay(rightNodeB)
        splayUntilSonOfRoot(rightNodeA, rightNodeB)
        rightNodeB?.rightSon = null
        refreshWeights(rightNodeB)
        rightNodeA?.parent = null

        rightNodeA = removeFirst(rightNodeA)
        merge(leftNodeA, rightNodeA)

        edges[left].remove(right)
        edges[right].remove(left)
    }

    fun directlyConnected(left: Int, right: Int): Boolean {
        return edges[left].containsKey(right)
    }

    fun connected(left: Int, right: Int): Boolean {
        var first: Node? = nodes[left].iterator().next()
        var second: Node? = nodes[right].iterator().next()
        while (first?.parent != null) {
            first = first.parent
        }
        while (second?.parent != null) {
            second = second.parent
        }
        return first === second
    }

    fun sizeOfNodesComponent(index: Int): Int {
        val node: Node = nodes[index].iterator().next()
        splay(node)
        return (node.weight + 1) / 2
    }

    private fun zigUntilSonOfRoot(node: Node, isLeftSon: Boolean) {
        val parent = node.parent
        val grandparent = parent?.parent
        var secondLeft = false
        if (grandparent?.leftSon === parent) {
            secondLeft = true
        }
        zig(node, isLeftSon)
        node.parent = grandparent
        if (secondLeft) {
            grandparent?.leftSon = node
        } else {
            grandparent?.rightSon = node
        }
    }

    private fun zig(node: Node, isLeftSon: Boolean) {
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

    private fun zigZig(node: Node, isLeftSon: Boolean) {
        val parent = node.parent
        val grandparent = parent?.parent
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

    private fun zigZag(node: Node, isLeftSon: Boolean) {
        val parent = node.parent
        val grandparent = parent?.parent
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

    private fun splay(node: Node?) {
        while (node?.parent != null) {
            val parent = node.parent
            var isLeftSon = false
            if (parent?.leftSon === node) {
                isLeftSon = true
            }
            if (parent?.parent == null) {
                zig(node, isLeftSon)
            } else {
                var secondLeft = false
                val grandparent = parent.parent
                if (grandparent?.leftSon === parent) {
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

    private fun addToTheLeft(node: Node?, value: Int): Node {
        if (node == null) {
            val newNode = Node(value)
            nodes[value].add(newNode)
            return newNode
        }
        val firstNode = findFirst(node)
        val newNode = Node(value)
        nodes[value].add(newNode)
        firstNode!!.leftSon = newNode
        refreshWeights(firstNode)
        newNode.parent = firstNode
        edges[value].put(firstNode.value, newNode)
        backEdges[value].put(firstNode.value, firstNode)
        return node
    }

    private fun addToTheRight(node: Node?, value: Int): Node {
        if (node == null) {
            val newNode = Node(value)
            nodes[value].add(newNode)
            return newNode
        }
        val lastNode = findLast(node)
        val newNode = Node(value)
        nodes[value].add(newNode)
        lastNode!!.rightSon = newNode
        refreshWeights(lastNode)
        newNode.parent = lastNode
        edges[lastNode.value].put(newNode.value, lastNode)
        backEdges[lastNode.value].put(newNode.value, newNode)
        return node
    }

    private fun removeFirst(node: Node?): Node? {
        val first = findFirst(node)
        nodes[first!!.value].remove(first)

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

    private fun findFirst(node: Node?): Node? {
        var node: Node? = node ?: return null
        while (node!!.parent != null) {
            node = node.parent
        }
        while (node!!.leftSon != null) {
            node = node.leftSon
        }
        return node
    }

    private fun findLast(node: Node?): Node? {
        var node: Node? = node ?: return null
        while (node!!.parent != null) {
            node = node.parent
        }
        while (node!!.rightSon != null) {
            node = node.rightSon
        }
        return node
    }

    private fun merge(left: Node?, right: Node?) {
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
        edges[lastLeft.value].put(firstRight!!.value, lastLeft)
        backEdges[lastLeft.value].put(firstRight.value, firstRight)
    }

    private fun splayUntilSonOfRoot(node: Node?, neededParent: Node?) {
        while (node?.parent !== neededParent && node?.parent != null) {
            val parent = node.parent
            var isLeftSon = false
            if (parent?.leftSon === node) {
                isLeftSon = true
            }
            if (parent?.parent === neededParent) {
                zigUntilSonOfRoot(node, isLeftSon)
            } else {
                var secondLeft = false
                val grandparent = parent?.parent
                if (grandparent?.leftSon === parent) {
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

    private fun refreshWeights(node: Node?) {
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