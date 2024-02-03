function restPostRequest(resourcePUri, data, consumerFun){
    const sendData=new URLSearchParams(data)
    fetch(resourcePUri,{
        method:"POST",
        body:sendData,
        headers:{

    }
    }).then(stopThemPromise, function (error){
        alert(error)
    })
    function stopThemPromise(promiseResponse) {
        if (promiseResponse.body != null) {
            promiseResponse.text().then(function (txt) {
                try {
                    consumerFun(JSON.parse(txt));
                } catch (e) {
                    consumerFun()
                }
            }, consumerFun)

            // promiseResponse.json().then((damnit)=>consumerFun(damnit))

        } else consumerFun();
    }

}


function restPostBodyRequest(resourcePUri, data, consumerFun){
    fetch( resourcePUri, {
        method: "POST",
        body: JSON.stringify(data),//todo handle cors policy with option post sent
        headers: {
            "Content-type": "application/json"

        }
    }).then(stopThemPromise,function (err){
        err.text().then((ert)=>alert(ert))
    })
    function stopThemPromise(promiseResponse) {
        if (promiseResponse.body != null) {
            promiseResponse.text().then(function (txt) {
                try {
                    consumerFun(JSON.parse(txt));
                } catch (e) {
                    consumerFun()
                }
            }, consumerFun)

            // promiseResponse.json().then((damnit)=>consumerFun(damnit))

        } else consumerFun();
    }
}


function restGetRequest(resourcePUri,consumerFun, servletBurl){
    fetch(resourcePUri,{
        method:"get",
        headers:{
            "Content-type":"application/x-www-form-urlencoded"
        }
    }).then((response) => response.json())
        .then((res) => {
            if(res.status!==200 && res.hasOwnProperty("status") ){}
            else consumerFun(res, servletBurl)
        });

}