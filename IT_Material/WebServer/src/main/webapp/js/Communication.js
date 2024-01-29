function restPostRequest(resourcePUri, jsonBody, consumerFun){
    fetch(resourcePUri,{
        method:"post",
        body:jsonBody,
        headers:{
            "Content-type": "application/json; charset=UTF-8"
        }
    }).then((response) => response.json())
        .then((json) => consumerFun(json));
}

function restGetRequest(resourcePUri,consumerFun, servletBurl){
    fetch("http://localhost:8080"+resourcePUri,{
        method:"get",
        headers:{
            "Content-type": "application/json; charset=UTF-8"
        }
    }).then((response) => response.json())
        .then((json) => consumerFun(json, servletBurl));
}