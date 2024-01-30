function restPostRequest(resourcePUri, formdata, consumerFun){

    fetch("http://localhost:8080"+resourcePUri,{
        method:"POST",
        body:formdata,
        headers:{

    }
    })
.then((response) => response.json()) //2
       .then((jj) => {
           //console.log(jj); //3
            consumerFun(jj)
       });

    /*const doFun = () => {
        res.then((a) => {
            consumerFun(a);
        });
    };

    doFun();*/
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