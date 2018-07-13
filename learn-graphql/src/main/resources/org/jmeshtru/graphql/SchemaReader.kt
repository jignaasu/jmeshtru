package org.jmeshtru.graphql

import graphql.schema.StaticDataFetcher
import graphql.schema.idl.RuntimeWiring

object SchemaReader {

    @JvmStatic
    fun main(args: Array<String>) {

    }

    @JvmStatic
    fun buildRuntimeWiring(): RuntimeWiring {
        return RuntimeWiring.newRuntimeWiring()
                .type("QueryType", { it -> it.
                dataFetcher("hero", StaticDataFetcher("JEDI"))})
                .build()
    }
}