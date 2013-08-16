(function ($) {
	EditorComponent= function (editorElement) {
		var editorObject=null;
		var config={
			items:[
				'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'cut', 'copy', 'paste',
				'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
				'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
				'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
				'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
				'table', 'hr', 'emoticons', 'pagebreak',
				'anchor', 'link', 'unlink'
			],
			imageTabIndex:1,
			uploadJson:"",
			extraFileUploadParams:{}
		};
		this.initComponent=function(options){
			var settings = $.extend(config,options);
			settings.uploadJson=options.uploadUrl;
			settings.extraFileUploadParams=options.uploadParams;
			KindEditor.ready(function(K){
				editorObject=$.extend(editorObject,K.create(editorElement,settings));
			});
		};
		this.html=function(){
			return editorObject.html();
		};
		this.text=function(){
			return editorObject.text();
		};
		this.insertHtml=function(html){
			editorObject.insertHtml(html);
		};
	};
})(jQuery);