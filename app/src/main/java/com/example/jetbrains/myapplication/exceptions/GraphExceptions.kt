package com.example.jetbrains.myapplication.exceptions

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by jetbrains on 17/12/2017.
 */

open class GraphException(cause: String) : Exception(cause)

sealed class GraphGenerationException(cause: String) : GraphException(cause)

sealed class IncorrectGraphParametersException(descr: String) :
        GraphGenerationException("incorrect graph size($descr)")

class IncorrectNumberOfVertexesException(n: Int) :
        IncorrectGraphParametersException("number of vertexes ($n) should be positive number " +
                "and shouldn't exceed 20")

class IncorrectNumberOfEdgesException(m: Int) :
        IncorrectGraphParametersException("number of edges ($m) should be positive number " +
                "and shouldn't exceed max(20, numberOfVertexes^2 / 2)")

class UnableToBuildAcyclicGraphException(n: Int, m: Int) : IncorrectGraphParametersException(
        "sorry, we failed to build acyclic graph with given n = $n, m = $m")

