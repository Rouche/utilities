package org.kitfox.json;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * @author Jean-Francois Larouche (resolutech) on 2020-03-24
 */
@Slf4j
public class JsonPathTest {
    @Test
    public void testModifyingValuesInJson() {

        Date original = new Date();

        String dateString = Optional.ofNullable(original)
                .map(date -> date.toInstant())
                .map(instant -> instant.toString())
                .orElse("");

        log.info("Date: [{}]", dateString);

        original = null;
        dateString = Optional.ofNullable(original)
                .map(date -> date.toInstant())
                .map(instant -> instant.toString())
                .orElse("");

        log.info("Date: [{}]", dateString);
    }
//
//    private fun parseRulesTreeForRadius(rules: String?): String {
//        var conf = Configuration.builder().jsonProvider(GsonJsonProvider()).mappingProvider(GsonMappingProvider()).build();
//
//        var ctx: ReadContext = JsonPath.using(conf).parse(rules);
//
//        val root = ctx.json() as JsonObject
//        recurseRulesTree(root)
//
//        return ctx.jsonString()
//    }
//
//    private fun recurseRulesTree(node: JsonObject) {
//        if(node.get("type").asString == "spatial") {
//            maybeConvertRadiusToRadiant(node)
//        } else {
//            val fields = node.get("fields")?.asJsonArray
//            if(fields != null) {
//                traverseRulesArray(fields)
//            }
//        }
//    }
//
//    private fun traverseRulesArray(array: JsonArray) {
//        array.forEach {
//            recurseRulesTree(it as JsonObject)
//        }
//    }
//
//    private fun maybeConvertRadiusToRadiant(spacial: JsonObject) {
//        val bound = spacial.get("bound")?.asJsonObject
//        val radius = bound?.get("radius")?.asFloat
//
//        if(radius != null) {
//            val earthPI = Math.PI * 6378137.0
//            val radiant = ((180 * radius / earthPI) * 1000).toFloat()
//            bound.addProperty("radius", radiant)
//        }
//    }
}
