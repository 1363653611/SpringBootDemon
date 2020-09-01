/**
 * 1. 对模块的加载行为进行自定义,写在主模块（index.js）的头部
 * 2. 参数就是一个对象，这个对象的paths属性指定各个模块的加载路径
 * 3. require.js要求，每个模块是一个单独的js文件。这样的话，如果加载多个模块，就会发出多次HTTP请求，会影响网页的加载速度
 */
require.config({
	//baseUrl参数指定本地模块(html)位置的基准目录，即本地模块的路径是相对于哪个目录的。该属性通常由require.js加载时的data-main属性指定.默认的baseUrl为包含RequireJS的那个HTML页面的所属目录。
	baseUrl: '../js',
	//paths参数指定各个模块的位置。这个位置可以是同一个服务器上的相对位置，也可以是外部网址。
	paths:{
		'jquery': ['./lib/jquery-3.4.1','https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js'],
		'index': './module/index',
	},
	//有些库不是AMD兼容的，这时就需要指定shim属性的值。shim可以理解成“垫片”，用来帮助require.js加载非AMD规范的库
	shim: {
		// underscore 模块，外部调用时使用 _ 指代该模块
		'underscore': {
			exports: '_'
		},
		//插件形式的非AMD模块，我们经常会用到jquery插件，而且这些插件基本都不符合AMD规范，比如jquery.form插件，这时候就需要将form插件"垫"到jquery中
        "jquery.form" : {
            deps : ["jquery"]
        },
		// backbone模块，外部调用时使用 Backbone 指代该模块
		// 这个模块依赖于 underscore , jquery 模块
		'backbone': {
			deps: ['underscore', 'jquery'],
			exports: 'Backbone'
		}
	},
	map: {
		// '*': {
		// 	'foo': 'foo1.2'
		// },
		// 'some/oldmodule': {
		// 	'foo': 'foo1.0'
		// }
	}
	
})
/**
 * 主模块的写法
 * 1. require()函数接受两个参数。
 * 第一个参数是一个数组，表示所依赖的模块，
 * 第二个参数是一个回调函数，当前面指定的模块都加载成功后，它将被调用
 * 2. require()异步加载moduleA，moduleB和moduleC，浏览器不会失去响应；它指定的回调函数，只有前面的模块都加载成功后，才会运行，解决了依赖性的问题。
 */
require(['index',"jquery"], function(index,$){
　　　　// some code here
	$("#testButton").click(function () {
		index.alertSome();
	})
})