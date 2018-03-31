<@includes>

<h1 class="page-header">Add Region</h1>

 <a class="btn btn-primary" data-toggle="modal" href='#modal-id'>Upload Regions</a>
  <br /><br />
 <div class="modal fade" id="modal-id">
 	<div class="modal-dialog">
 		<form action="<@spring.url '/dashboard/regions/upload'/>" method="POST" enctype="multipart/form-data" role="form">
 		<div class="modal-content">
 			<div class="modal-header">
 				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
 				<h4 class="modal-title">Upload Regions</h4>
 			</div>
 			<div class="modal-body">
	 			<div class="form-group">
	 				<label for="file">File</label>
	 				<input type="file" name="file" id="file" required>
	 			</div> 				
 			</div>
 			<div class="modal-footer">
 				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
 				<button type="submit" class="btn btn-primary">Upload</button>
 			</div>
 		</div>
 		</form> 		
 	</div>
 </div>
 
<form action="<@spring.url '/dashboard/regions'/>" method="POST" enctype="multipart/form-data" role="form">
	
	<#include "_form.ftl">

	<button type="submit" class="btn btn-primary">Submit</button>
</form>

</@includes>