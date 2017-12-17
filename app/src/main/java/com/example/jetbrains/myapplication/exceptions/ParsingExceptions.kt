package com.example.jetbrains.myapplication.exceptions

/**
 * Created by jetbrains on 17/12/2017.
 */

private const val LINE_EXAMPLE_TEMPLATE = "<edge (u, v) : u, v in [1..n]> (line"
private const val LINE_EXAMPLES = "$LINE_EXAMPLE_TEMPLATE 1)\n" +
        "$LINE_EXAMPLE_TEMPLATE 2)\n" +
        "...........\n" +
        "$LINE_EXAMPLE_TEMPLATE m)\n"
private const val N_M_DESCRIPTION = "try again and follow the input format, please: \n" +
        "<number of vertexes(n) [1..20]> <number of edges(m)> [1..min(20, n^2 /2)]\n"
private const val DESCRIPTION = N_M_DESCRIPTION + LINE_EXAMPLES

class GrapgParsingException: Exception(DESCRIPTION)