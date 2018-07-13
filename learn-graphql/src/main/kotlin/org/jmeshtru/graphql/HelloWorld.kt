package org.jmeshtru.graphql

import graphql.GraphQL
import graphql.schema.StaticDataFetcher
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser

object HelloWorld {

    @JvmStatic
    fun main(args: Array<String>) {
        val schema = "type Query{hello: String}"

        val schemaParser = SchemaParser()
        val typeDefinitionRegistry = schemaParser.parse(schema)
        val runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", { it -> it.dataFetcher("hello", StaticDataFetcher("Rama")) })
                .build()

        val schemaGenerator = SchemaGenerator()
        val graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring)

        val build = GraphQL.newGraphQL(graphQLSchema).build()
        val executionResult = build.execute("{hello}")
        println(executionResult.getData<Any>().toString())
    }
}