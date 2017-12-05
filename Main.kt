

fun LowestCommonAncestor(size : Int,edges : List<Pair<Int, Int>>,testx : List<Pair<Int, Int>>):Array<Int>{

    val graph : Array<ArrayList<Int>> = Array(size,{ ArrayList<Int>() } );
    val path : Array<Int> = Array(size,{-1});
    val head : Array<Int> = Array(size,{-1});
    val heavy : Array<Int> = Array(size,{-1});
    val depth : Array<Int> = Array(size,{-1});
    val sizes : Array<Int> = Array(size,{-1});
    val p : Array<Int> = Array(size,{0});

    fun DeepFirstSearch(node : Int){
        sizes[node] = 1;
        for (i in graph[node].indices){
            var to : Int = graph[node][i];
            if (to != p[node]){
                p[to] = node;
                depth[to] = depth[node] + 1;
                DeepFirstSearch(to);
                sizes[node] += sizes[to];
                if (heavy[node] == -1 || sizes[to] > sizes[heavy[node]])
                    heavy[node] = to;
            }
        }
    }

    fun HeavyLightDecomposition(node : Int){
        DeepFirstSearch(node);
        var cnt : Int = 0;
        var i : Int = 0;
        while (i < size){
            if (p[i] == -1 || heavy[p[i]] != i){
                var  j : Int = i;
                while (j != -1){
                    path[j] = cnt;
                    head[j] = i;
                    j = heavy[j];
                }
            }
            cnt++;
            i++
        }
    }

    fun Answer( aa : Int,bb : Int):Int{
        var a : Int = aa;
        var b : Int = bb;
        while (path[a] != path[b]){
            if (depth[head[a]] < depth[head[b]])
                a = b.also{ b = a};
            a = p[head[a]];
        }
        if (depth[a] > depth[b])
            a = b.also{ b = a};
        return a;
    }

    for (i in edges.indices) {
        graph[edges[i].first - 1].add(edges[i].second - 1);
        graph[edges[i].second - 1].add(edges[i].first - 1);
    }
    p[0] = -1;
    HeavyLightDecomposition(0);
    var ans : Array<Int> = Array(testx.size,{0});
    for (i in testx.indices){
        ans[i] = (Answer(testx[i].first - 1,testx[i].second - 1)) + 1;
    }
    return ans;
}
