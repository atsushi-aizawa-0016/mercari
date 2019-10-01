$(function(){
    $("#parentCategoryPulldown").change(function(){
        var value = $("#parentCategoryPulldown option:selected").val();
        $.get("parentCategory/" + value ,function(data){
            var obj = $.parseJSON(data);
            $("#childCategoryPulldown").html("");
            for(var i=0;i<obj.length;i++){
                $("#childCategoryPulldown").append("<option value='"+obj[i].value+"'>"+obj[i].label+"</option>");
            }
        })
    })
    $("#childCategoryPulldown").change(function(){
        var value = $("#childCategoryPulldown option:selected").val();
        $.get("childCategory/" + value ,function(data){
            var obj = $.parseJSON(data);
            $("#grandChildCategoryPulldown").html("");
            for(var i=0;i<obj.length;i++){
                $("#grandChildCategoryPulldown").append("<option value="+obj[i].value+">"+obj[i].label+"</option>");
            }
        })
    })
})

////フォームの初期化
//function clearAll(){
//	document.getElementById('childCategoryInput').val() = '';
//	document.getElementById('parentCategoryInput').val() = '';
//	document.getElementById('childCategory').val() = '';
//	document.getElementById('parentCategory').val() = '';
//}
//
////JSONデータ（中カテゴリーのmap？）を大カテゴリーのプルダウンにセットする
//function setPulldownChildCategory(data){
//	var obj = $.parseJSON(data);
//	$('#childCategoryPulldown').html('');
//	for (var i = 0; i < obj.length; i++) {
//		$('#childCategoryPulldown').append(
//				'<option value=' + obj[i].value + '>' + obj[i].label + '</option>');
//	}
//}
//
////大カテゴリーのプルダウンを最適化
//function refreshChildCategory(parentCategory){
//	var deferred = new Deferred();
//	$.get('/parentCategory/' + parentCategory,function(data){
//		setPulldown(data);
//	}).always(function(){
//		deferred.resolve();
//	});
//	return deferred; 
//}
//
////仕事選択
//$('#childCategoryPullDown').change(function() {
//	var childCategory = $('#childCategoryPullDown option:selected').val();
//	document.getElementById('childCategoryInput').value = childCategory;
//});
//
////職種選択時に仕事のプルダウンをリフレッシュする
//$('#parentCategoryPullDown').change(function() {
//	var parentCategory = $('#parentCategoryPullDown option:selected').val();
//	document.getElementById('parentCategoryInput').value = parentCategory;
//	refreshParentCategory(parentCategory);
//});
//
////フォーム送信
//function submitCategory() {
//	var parentCategory = document.getElementById('parentCategoryInput').value;
//	document.getElementById('parentCategory').value = parentCategory;
//	var childCategory = document.getElementById('childCategoryInput').value;
//	document.getElementById('childCategory').value = childCategory;
//}
// 
//

