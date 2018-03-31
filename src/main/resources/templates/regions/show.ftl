<@includes>

 <h1 class="page-header">${region.name}</h1>
 
  <a class="btn btn-primary" data-toggle="modal" href='#modal-id'>Add Districts</a>
  <br /><br />
 <div class="modal fade" id="modal-id">
 	<div class="modal-dialog">
 		<form action="<@spring.url '/dashboard/regions/${region.id}/districts'/>" method="POST" role="form">
 		<div class="modal-content">
 			<div class="modal-header">
 				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
 				<h4 class="modal-title">Add Districts</h4>
 			</div>
 			<div class="modal-body">
	 			<div class="form-group">
	 				<label for="districtsString">Districts</label>
	 				<textarea name="districtsString" class="form-control" rows="10"></textarea>
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
     <th>Region</th>
     <th>Date Added</th>
     </thead>
     <tbody>
 		<#list region.districts as district>
        <tr>
            <td>${district.id}.</td>
            <td>
                <a href="<@spring.url '/dashboard/regions/${region.id}/districts/${district.id}'/>">${district.name}</a>
            </td>
            <td>${district.region.name}</td>
            <td>${district.createdAt}</td>
        </tr>
        </#list>
     </tbody>
 </table>

</@includes>