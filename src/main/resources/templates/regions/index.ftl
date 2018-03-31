<@includes>

 <h1 class="page-header">Regions</h1>
 
 <a href="<@spring.url '/dashboard/regions/create'/>" class="btn btn-primary">Add</a><br /><br />
 
 <table class="table table-bordered">
 	<thead>
 		<th>SN</th>
 		<th>Name</th>
 		<th>Date Added</th>
 	</thead>
 	<tbody>
 		<#list regions as region>
 			<tr>
 				<td>${region.id}.</td>
 				<td>${region.name}</td>
 				<td>${region.createdAt}</td>
 			</tr>
 		</#list>
 	</tbody>
 </table>

</@includes>