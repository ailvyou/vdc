<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>菜单列表 - Powered By VDC</title>
<meta name="author" content="VDC Team" />
<meta name="copyright" content="VDC" />
<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/list.js"></script>
<style type="text/css">
.moreTable th {
	width: 80px;
	line-height: 25px;
	padding: 5px 10px 5px 0px;
	text-align: right;
	font-weight: normal;
	color: #333333;
	background-color: #f8fbff;
}

.moreTable td {
	line-height: 25px;
	padding: 5px;
	color: #666666;
}

.promotion {
	color: #cccccc;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $listForm = $("#listForm");
	var $moreButton = $("#moreButton");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	
	
	// 更多选项
	$moreButton.click(function() {
		$.dialog({
			title: "更多选项",
content: ' <table id="moreTable" class="moreTable"> <tr> <th> 商品分类: <\/th> <td> <select name="productCategoryId"> <option value="">请选择...<\/option> <option value="1"> 时尚女装 <\/option> <option value="11"> &nbsp;&nbsp; 连衣裙 <\/option> <option value="12"> &nbsp;&nbsp; 针织衫 <\/option> <option value="13"> &nbsp;&nbsp; 短外套 <\/option> <option value="14"> &nbsp;&nbsp; 小西装 <\/option> <option value="15"> &nbsp;&nbsp; 牛仔裤 <\/option> <option value="16"> &nbsp;&nbsp; T恤 <\/option> <option value="17"> &nbsp;&nbsp; 衬衫 <\/option> <option value="18"> &nbsp;&nbsp; 风衣 <\/option> <option value="19"> &nbsp;&nbsp; 卫衣 <\/option> <option value="20"> &nbsp;&nbsp; 裤子 <\/option> <option value="2"> 精品男装 <\/option> <option value="21"> &nbsp;&nbsp; 针织衫 <\/option> <option value="22"> &nbsp;&nbsp; POLO衫 <\/option> <option value="23"> &nbsp;&nbsp; 休闲裤 <\/option> <option value="24"> &nbsp;&nbsp; 牛仔裤 <\/option> <option value="25"> &nbsp;&nbsp; T恤 <\/option> <option value="26"> &nbsp;&nbsp; 衬衫 <\/option> <option value="27"> &nbsp;&nbsp; 西服 <\/option> <option value="28"> &nbsp;&nbsp; 西裤 <\/option> <option value="29"> &nbsp;&nbsp; 风衣 <\/option> <option value="30"> &nbsp;&nbsp; 卫衣 <\/option> <option value="3"> 精致内衣 <\/option> <option value="31"> &nbsp;&nbsp; 女式内裤 <\/option> <option value="32"> &nbsp;&nbsp; 男式内裤 <\/option> <option value="33"> &nbsp;&nbsp; 保暖内衣 <\/option> <option value="34"> &nbsp;&nbsp; 塑身衣 <\/option> <option value="35"> &nbsp;&nbsp; 连裤袜 <\/option> <option value="36"> &nbsp;&nbsp; 打底袜 <\/option> <option value="37"> &nbsp;&nbsp; 文胸 <\/option> <option value="38"> &nbsp;&nbsp; 睡衣 <\/option> <option value="39"> &nbsp;&nbsp; 泳装 <\/option> <option value="40"> &nbsp;&nbsp; 浴袍 <\/option> <option value="4"> 服饰配件 <\/option> <option value="41"> &nbsp;&nbsp; 女士腰带 <\/option> <option value="42"> &nbsp;&nbsp; 男士皮带 <\/option> <option value="43"> &nbsp;&nbsp; 女士围巾 <\/option> <option value="44"> &nbsp;&nbsp; 男士围巾 <\/option> <option value="45"> &nbsp;&nbsp; 帽子 <\/option> <option value="46"> &nbsp;&nbsp; 手套 <\/option> <option value="47"> &nbsp;&nbsp; 领带 <\/option> <option value="48"> &nbsp;&nbsp; 领结 <\/option> <option value="49"> &nbsp;&nbsp; 发饰 <\/option> <option value="50"> &nbsp;&nbsp; 袖扣 <\/option> <option value="5"> 时尚女鞋 <\/option> <option value="51"> &nbsp;&nbsp; 高帮鞋 <\/option> <option value="52"> &nbsp;&nbsp; 雪地靴 <\/option> <option value="53"> &nbsp;&nbsp; 中筒靴 <\/option> <option value="54"> &nbsp;&nbsp; 单鞋 <\/option> <option value="55"> &nbsp;&nbsp; 凉鞋 <\/option> <option value="56"> &nbsp;&nbsp; 靴子 <\/option> <option value="57"> &nbsp;&nbsp; 短靴 <\/option> <option value="58"> &nbsp;&nbsp; 雨靴 <\/option> <option value="6"> 流行男鞋 <\/option> <option value="59"> &nbsp;&nbsp; 低帮鞋 <\/option> <option value="60"> &nbsp;&nbsp; 高帮鞋 <\/option> <option value="61"> &nbsp;&nbsp; 休闲鞋 <\/option> <option value="62"> &nbsp;&nbsp; 正装鞋 <\/option> <option value="7"> 潮流女包 <\/option> <option value="63"> &nbsp;&nbsp; 单肩包 <\/option> <option value="64"> &nbsp;&nbsp; 双肩包 <\/option> <option value="65"> &nbsp;&nbsp; 手提包 <\/option> <option value="66"> &nbsp;&nbsp; 手拿包 <\/option> <option value="8"> 精品男包 <\/option> <option value="67"> &nbsp;&nbsp; 单肩男 <\/option> <option value="68"> &nbsp;&nbsp; 双肩包 <\/option> <option value="69"> &nbsp;&nbsp; 手提包 <\/option> <option value="70"> &nbsp;&nbsp; 手拿包 <\/option> <option value="9"> 童装童鞋 <\/option> <option value="71"> &nbsp;&nbsp; 运动鞋 <\/option> <option value="72"> &nbsp;&nbsp; 牛仔裤 <\/option> <option value="73"> &nbsp;&nbsp; 套装 <\/option> <option value="74"> &nbsp;&nbsp; 裤子 <\/option> <option value="10"> 时尚饰品 <\/option> <option value="75"> &nbsp;&nbsp; 项链 <\/option> <option value="76"> &nbsp;&nbsp; 手链 <\/option> <option value="77"> &nbsp;&nbsp; 戒指 <\/option> <option value="78"> &nbsp;&nbsp; 耳饰 <\/option> <\/select> <\/td> <\/tr> <tr> <th> 品牌: <\/th> <td> <select name="brandId"> <option value="">请选择...<\/option> <option value="1"> 梵希蔓 <\/option> <option value="2"> 伊芙丽 <\/option> <option value="3"> 尚都比拉 <\/option> <option value="4"> 森马 <\/option> <option value="5"> 以纯 <\/option> <option value="6"> 李宁 <\/option> <option value="7"> 耐克 <\/option> <option value="8"> 阿迪达斯 <\/option> <option value="9"> Jack Jones <\/option> <option value="10"> 七匹狼 <\/option> <option value="11"> 恒源祥 <\/option> <option value="12"> 圣得西 <\/option> <option value="13"> 猫人 <\/option> <option value="14"> 北极绒 <\/option> <option value="15"> 美特斯·邦威 <\/option> <option value="16"> 真维斯 <\/option> <option value="17"> 唐狮 <\/option> <\/select> <\/td> <\/tr> <tr> <th> 促销: <\/th> <td> <select name="promotionId"> <option value="">请选择...<\/option> <option value="1"> 限时抢购 <\/option> <option value="2"> 双倍积分 <\/option> <\/select> <\/td> <\/tr> <tr> <th> 标签: <\/th> <td> <select name="tagId"> <option value="">请选择...<\/option> <option value="1"> 热销 <\/option> <option value="2"> 最新 <\/option> <\/select> <\/td> <\/tr> <\/table>',			width: 470,
			modal: true,
			ok: "确&nbsp;&nbsp;定",
			cancel: "取&nbsp;&nbsp;消",
			onOk: function() {
				$("#moreTable :input").each(function() {
					var $this = $(this);
					$("#" + $this.attr("name")).val($this.val());
				});
				$listForm.submit();
			}
		});
	});
	
	// 商品筛选
	$filterSelect.mouseover(function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	// 筛选选项
	$filterOption.click(function() {
		var $this = $(this);
		var $dest = $("#" + $this.attr("name"));
		if ($this.hasClass("checked")) {
			$dest.val("");
		} else {
			$dest.val($this.attr("val"));
		}
		$listForm.submit();
		return false;
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="/admin/common/index.jhtml">首页</a> &raquo; 菜单列表 <span>(共<span id="pageTotal">300</span>条记录)</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<input type="hidden" id="productCategoryId" name="productCategoryId" value="" />
		<input type="hidden" id="brandId" name="brandId" value="" />
		<input type="hidden" id="promotionId" name="promotionId" value="" />
		<input type="hidden" id="tagId" name="tagId" value="" />
		<input type="hidden" id="isMarketable" name="isMarketable" value="" />
		<input type="hidden" id="isList" name="isList" value="" />
		<input type="hidden" id="isTop" name="isTop" value="" />
		<input type="hidden" id="isGift" name="isGift" value="" />
		<input type="hidden" id="isOutOfStock" name="isOutOfStock" value="" />
		<input type="hidden" id="isStockAlert" name="isStockAlert" value="" />
		<div class="bar">
			<a href="${ctx}/system/menu/edit" class="iconButton">
				<span class="addIcon">&nbsp;</span>添加
			</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						商品筛选<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
							<li>
								<a href="javascript:;" name="isMarketable" val="true">已上架</a>
							</li>
							<li>
								<a href="javascript:;" name="isMarketable" val="false">未上架</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="isList" val="true">已列出</a>
							</li>
							<li>
								<a href="javascript:;" name="isList" val="false">未列出</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="isTop" val="true">已置顶</a>
							</li>
							<li>
								<a href="javascript:;" name="isTop" val="false">未置顶</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="isGift" val="true">赠品</a>
							</li>
							<li>
								<a href="javascript:;" name="isGift" val="false">非赠品</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="isOutOfStock" val="false">有货</a>
							</li>
							<li>
								<a href="javascript:;" name="isOutOfStock" val="true">缺货</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="isStockAlert" val="false">库存正常</a>
							</li>
							<li>
								<a href="javascript:;" name="isStockAlert" val="true">库存警告</a>
							</li>
						</ul>
					</div>
				</div>
				<a href="javascript:;" id="moreButton" class="button">更多选项</a>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						每页显示<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;" val="10">10</a>
							</li>
							<li>
								<a href="javascript:;" class="current" val="20">20</a>
							</li>
							<li>
								<a href="javascript:;" val="50">50</a>
							</li>
							<li>
								<a href="javascript:;" val="100">100</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" value="" maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" val="name">名称</a>
						</li>
						<li>
							<a href="javascript:;" val="sn">编号</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="sn">编号</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="name">名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="productCategory">商品分类</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="price">销售价</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="cost">成本价</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="stock">库存</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isMarketable">是否上架</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="300" />
					</td>
					<td>
						201304529
					</td>
					<td>
						<span title="尚都比拉女装2013夏装新款蕾丝连衣裙 韩版修身雪纺打底裙子 春款[白色 M]">
							尚都比拉女装2013夏装新款蕾丝连衣裙 韩版修身雪纺打底...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						298.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:20:02">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=300">[编辑]</a>
							<a href="/product/content/201306/300.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="299" />
					</td>
					<td>
						201304528
					</td>
					<td>
						<span title="尚都比拉女装2013夏装新款蕾丝连衣裙 韩版修身雪纺打底裙子 春款[白色 L]">
							尚都比拉女装2013夏装新款蕾丝连衣裙 韩版修身雪纺打底...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						298.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:20:01">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=299">[编辑]</a>
							<a href="/product/content/201306/299.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="298" />
					</td>
					<td>
						201304527
					</td>
					<td>
						<span title="尚都比拉2013夏装新款淑女装 春款森女系 碎花修身短袖蕾丝连衣裙[粉红色 S]">
							尚都比拉2013夏装新款淑女装 春款森女系 碎花修身短袖...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						289.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:20:00">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=298">[编辑]</a>
							<a href="/product/content/201306/298.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="297" />
					</td>
					<td>
						201304526
					</td>
					<td>
						<span title="尚都比拉2013夏装新款淑女装 春款森女系 碎花修身短袖蕾丝连衣裙[粉红色 M]">
							尚都比拉2013夏装新款淑女装 春款森女系 碎花修身短袖...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						289.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:59">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=297">[编辑]</a>
							<a href="/product/content/201306/297.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="296" />
					</td>
					<td>
						201304525
					</td>
					<td>
						<span title="尚都比拉2013夏装新款淑女装 春款森女系 碎花修身短袖蕾丝连衣裙[粉红色 L]">
							尚都比拉2013夏装新款淑女装 春款森女系 碎花修身短袖...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						289.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:58">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=296">[编辑]</a>
							<a href="/product/content/201306/296.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="295" />
					</td>
					<td>
						201304524
					</td>
					<td>
						<span title="尚都比拉2013夏装新款淑女装 春款森女系 碎花修身短袖蕾丝连衣裙[白色 M]">
							尚都比拉2013夏装新款淑女装 春款森女系 碎花修身短袖...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						289.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:57">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=295">[编辑]</a>
							<a href="/product/content/201306/295.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="294" />
					</td>
					<td>
						201304523
					</td>
					<td>
						<span title="尚都比拉2013夏装新款淑女装 春款森女系 碎花修身短袖蕾丝连衣裙[白色 XL]">
							尚都比拉2013夏装新款淑女装 春款森女系 碎花修身短袖...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						289.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:56">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=294">[编辑]</a>
							<a href="/product/content/201306/294.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="293" />
					</td>
					<td>
						201304522
					</td>
					<td>
						<span title="尚都比拉2013夏装新款女装 韩版淑女装 蕾丝雪纺连衣裙 春款短袖[白色 S]">
							尚都比拉2013夏装新款女装 韩版淑女装 蕾丝雪纺连衣裙...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						259.00
					</td>
					<td>
						
					</td>
					<td>
								<span class="red">0</span>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:55">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=293">[编辑]</a>
							<a href="/product/content/201306/293.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="292" />
					</td>
					<td>
						201304521
					</td>
					<td>
						<span title="尚都比拉2013夏装新款女装 韩版淑女装 蕾丝雪纺连衣裙 春款短袖[白色 M]">
							尚都比拉2013夏装新款女装 韩版淑女装 蕾丝雪纺连衣裙...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						259.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:54">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=292">[编辑]</a>
							<a href="/product/content/201306/292.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="291" />
					</td>
					<td>
						201304520
					</td>
					<td>
						<span title="尚都比拉2013夏装新款女装 韩版淑女装 蕾丝雪纺连衣裙 春款短袖[白色 L]">
							尚都比拉2013夏装新款女装 韩版淑女装 蕾丝雪纺连衣裙...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						259.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:53">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=291">[编辑]</a>
							<a href="/product/content/201306/291.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="290" />
					</td>
					<td>
						201304519
					</td>
					<td>
						<span title="尚都比拉2013夏装新款 韩版优雅甜美淑女装 春款蕾丝雪纺连衣裙子[绿色 M]">
							尚都比拉2013夏装新款 韩版优雅甜美淑女装 春款蕾丝雪...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						299.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:52">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=290">[编辑]</a>
							<a href="/product/content/201306/290.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="289" />
					</td>
					<td>
						201304518
					</td>
					<td>
						<span title="尚都比拉2013夏装新款 韩版优雅甜美淑女装 春款蕾丝雪纺连衣裙子[绿色 L]">
							尚都比拉2013夏装新款 韩版优雅甜美淑女装 春款蕾丝雪...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						299.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:51">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=289">[编辑]</a>
							<a href="/product/content/201306/289.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="288" />
					</td>
					<td>
						201304517
					</td>
					<td>
						<span title="尚都比拉2013夏装新款 韩版优雅甜美淑女装 春款蕾丝雪纺连衣裙子[绿色 XL]">
							尚都比拉2013夏装新款 韩版优雅甜美淑女装 春款蕾丝雪...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						299.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:50">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=288">[编辑]</a>
							<a href="/product/content/201306/288.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="287" />
					</td>
					<td>
						201304516
					</td>
					<td>
						<span title="尚都比拉2013夏装新款 韩版优雅淑女装 七分袖蕾丝雪纺连衣裙春款[白色 S]">
							尚都比拉2013夏装新款 韩版优雅淑女装 七分袖蕾丝雪纺...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						339.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:49">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=287">[编辑]</a>
							<a href="/product/content/201306/287.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="286" />
					</td>
					<td>
						201304515
					</td>
					<td>
						<span title="尚都比拉2013夏装新款 韩版优雅淑女装 七分袖蕾丝雪纺连衣裙春款[白色 M]">
							尚都比拉2013夏装新款 韩版优雅淑女装 七分袖蕾丝雪纺...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						339.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:48">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=286">[编辑]</a>
							<a href="/product/content/201306/286.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="285" />
					</td>
					<td>
						201304514
					</td>
					<td>
						<span title="尚都比拉2013夏装新款 春款修身淑女装 雪纺短袖假两件套连衣裙子[绿色 S]">
							尚都比拉2013夏装新款 春款修身淑女装 雪纺短袖假两件...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						269.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:47">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=285">[编辑]</a>
							<a href="/product/content/201306/285.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="284" />
					</td>
					<td>
						201304513
					</td>
					<td>
						<span title="尚都比拉2013夏装新款 春款修身淑女装 雪纺短袖假两件套连衣裙子[绿色 M]">
							尚都比拉2013夏装新款 春款修身淑女装 雪纺短袖假两件...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						269.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:46">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=284">[编辑]</a>
							<a href="/product/content/201306/284.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="283" />
					</td>
					<td>
						201304512
					</td>
					<td>
						<span title="尚都比拉2013夏装新款 春款修身淑女装 雪纺短袖假两件套连衣裙子[绿色 XL]">
							尚都比拉2013夏装新款 春款修身淑女装 雪纺短袖假两件...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						269.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:45">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=283">[编辑]</a>
							<a href="/product/content/201306/283.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="282" />
					</td>
					<td>
						201304511
					</td>
					<td>
						<span title="尚都比拉2013夏装新款 春款甜美淑女装 荷叶袖修身蕾丝雪纺连衣裙[白色 M]">
							尚都比拉2013夏装新款 春款甜美淑女装 荷叶袖修身蕾丝...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						269.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:44">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=282">[编辑]</a>
							<a href="/product/content/201306/282.html" target="_blank">[查看]</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="281" />
					</td>
					<td>
						201304510
					</td>
					<td>
						<span title="尚都比拉2013夏装新款 春款甜美淑女装 荷叶袖修身蕾丝雪纺连衣裙[白色 L]">
							尚都比拉2013夏装新款 春款甜美淑女装 荷叶袖修身蕾丝...
						</span>
							<span class="promotion">限时抢购</span>
					</td>
					<td>
						连衣裙
					</td>
					<td>
						269.00
					</td>
					<td>
						
					</td>
					<td>
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<span title="2013-06-13 00:19:43">2013-06-13</span>
					</td>
					<td>
						<a href="edit.jhtml?id=281">[编辑]</a>
							<a href="/product/content/201306/281.html" target="_blank">[查看]</a>
					</td>
				</tr>
		</table>
<input type="hidden" id="pageSize" name="pageSize" value="20" />
<input type="hidden" id="searchProperty" name="searchProperty" value="" />
<input type="hidden" id="orderProperty" name="orderProperty" value="" />
<input type="hidden" id="orderDirection" name="orderDirection" value="" />
	<div class="pagination">
			<span class="firstPage">&nbsp;</span>
			<span class="previousPage">&nbsp;</span>
				<span class="currentPage">1</span>
				<a href="javascript: $.pageSkip(2);">2</a>
				<a href="javascript: $.pageSkip(3);">3</a>
				<span class="pageBreak">...</span>
			<a class="nextPage" href="javascript: $.pageSkip(2);">&nbsp;</a>
			<a class="lastPage" href="javascript: $.pageSkip(15);">&nbsp;</a>
		<span class="pageSkip">
			共15页 到第<input id="pageNumber" name="pageNumber" value="1" maxlength="9" onpaste="return false;" />页<button type="submit">&nbsp;</button>
		</span>
	</div>
	</form>
</body>
</html>
