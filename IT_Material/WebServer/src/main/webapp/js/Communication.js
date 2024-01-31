function restPostRequest(resourcePUri, data, consumerFun){
    const sendData=new URLSearchParams(data)
    fetch("http://localhost:8080"+resourcePUri,{
        method:"POST",
        body:sendData,
        headers:{

    }
    }).then(stopThemPromise, function (error){
        alert(error)
    })

    function stopThemPromise(promiseResponse){
        if(promiseResponse.body!=null ) {
                promiseResponse.text().then(function (txt){
                    try {
                        consumerFun( JSON.parse(txt));
                    }catch (e){consumerFun()}
                },consumerFun)

               // promiseResponse.json().then((damnit)=>consumerFun(damnit))

        }else consumerFun();
    }

/*
.then((response) =>{//todo error catch
    if(response.body!=null){
        const rr=response.json()
        consumerFun(rr)
    }
    else consumerFun() }) //2

       .catch( (error)=> alert("com error")+error);*/
}
function restPostBodyRequest(resourcePUri, data, consumerFun){
    fetch("http://localhost:8080" + resourcePUri, {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-type": "application/json"

        }
    }).then(r  =>alert(r.statusText))
}
function restGetRequest(resourcePUri,consumerFun, servletBurl){
    fetch("http://localhost:8080"+resourcePUri,{
        method:"get",
        headers:{
            "Content-type":"application/x-www-form-urlencoded"
        }
    }).then((response) => response.json())
        .then((json) => consumerFun(json, servletBurl));
}