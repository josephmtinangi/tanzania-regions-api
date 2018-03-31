<@includes>

 <h1 class="page-header">${district.name}</h1>

 <a class="btn btn-primary" data-toggle="modal" href='#modal-id'>Upload Wards</a>
  <br /><br />
 <div class="modal fade" id="modal-id">
 	<div class="modal-dialog">
 		<form action="<@spring.url '/dashboard/regions/${district.region.id}/districts/${district.id}/wards'/>" method="POST" enctype="multipart/form-data" role="form">
 		<div class="modal-content">
 			<div class="modal-header">
 				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
 				<h4 class="modal-title">Upload Wards</h4>
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
 
 <table class="table table-bordered">
 	<thead>
 		<th>SN</th>
 		<th>Name</th>
 		<th>District</th>
 		<th>Date Added</th>
 	</thead>
 	<tbody>
 		<#list district.wards as ward>
 			<tr>
 				<td>${ward.id}.</td>
 				<td>
 					<a href="<@spring.url '/dashboard/regions/${district.region.id}/districts/${district.id}/wards/${ward.id}'/>">${ward.name}</a>
 				</td>
 				<td>${ward.district.name}</td>
 				<td>${ward.createdAt}</td>
 			</tr>
 		</#list>
 	</tbody>
 </table>

</@includes>