package org.minibatch.transformers

import org.json.JSONObject
import org.json.XML

class JSONXmlTransformer: Transformer {

    override fun transform(e: Any): String {
        try {
            return XML.toString(JSONObject(e.toString()))
        } catch (any: org.json.JSONException) {
            //Not valid JSON
            return e.toString()
        }
    }
}