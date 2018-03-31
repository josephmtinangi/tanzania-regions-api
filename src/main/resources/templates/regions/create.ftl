<@includes>

<h1 class="page-header">Add Region</h1>
 
<form action="<@spring.url '/dashboard/regions'/>" method="POST" enctype="multipart/form-data" role="form">
	
	<#include "_form.ftl">

	<button type="submit" class="btn btn-primary">Submit</button>
</form>

</@includes>