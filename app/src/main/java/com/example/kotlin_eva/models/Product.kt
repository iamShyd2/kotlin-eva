package com.example.kotlin_eva.models

import org.json.JSONObject

class Product {
    var id = 0
    var name = ""
    var image = ""
    var cost = 0

    constructor()

    constructor(name: String){
        this.name = name
    }

    companion object {
        @JvmStatic
        fun newInstance(jsonObject: JSONObject) =
            Product().apply {
                name = jsonObject.getString("name")
                image = jsonObject.getString("image")
                cost = jsonObject.getInt("cost")
                id = jsonObject.getInt("id")
            }
    }

}