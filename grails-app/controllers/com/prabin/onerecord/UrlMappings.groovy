package com.prabin.onerecord

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "dashBoard", action: "index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
