<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>Insert title here</title>
<style type="text/css">
.l {
	color: red;
}
</style>
</head>
<body>


<p class="text-info">Saghir</p><br><br>
<p class="text-info">Saghir</p><br><br>
<p class="text-info">Saghir</p><br>

<!-- <div class="controls">
   <a href="#" id="edit" class="btn">Edit</a>
</div> -->


<script src="//code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript">

$('p.text-info').dblclick(function() {
	 var text = $(this).text();
	 var input = $('<input id="attribute" type="text" value="' + text + '" />')
	 $(this).text('').append(input);
	 input.select();
	 var e;
	 input.blur(function() {
	   var text = $('#attribute').val();
	   $('#attribute').parent().text(text);
	   $('#attribute').remove();
	   /* e=$(this);
	   var text=e.val();
	   var cname=e.attr('class');
	   alert(cname);
	   alert(text);
	   alert(e);
	   e.style.color = "#ff0000" */
	 }); 
	 
	   $(this).css({
         "color" : "#a3a3c2",
                        
});
	});
</script>
</body>
</html>