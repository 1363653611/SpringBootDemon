define(['jquery'],function($){
    //变量定义区
    var moduleName = "hello module";
    var moduleVersion = "1.0";

    var alertSome = function alertSome(){
        $(".buttonOut").html("<p>click the button</p>")
    }
    return {
        alertSome: alertSome,
    }
})