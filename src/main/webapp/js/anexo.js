// The root URL for the RESTful services
var rootURL = "http://localhost:8080/AgendaAcademica/rest/anexo";

var currentanexo;

// Retrieve anexo list when application starts 
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});

$('#btnAdd').click(function() {
	newanexo();
	return false;
});

$('#btnSave').click(function() {
	if ($('#anexoId').val() == '')
		addanexo();
	else
		updateanexo();
	return false;
});

$('#btnDelete').click(function() {
	deleteanexo();
	return false;
});

$('#anexoList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic anexo bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newanexo() {
	$('#btnDelete').hide();
	currentanexo = {};
	renderDetails(currentanexo); // Display empty form
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
}

function findByName(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + searchKey,
		dataType: "json",
		success: renderList 
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentanexo = data;
			renderDetails(currentanexo);
		}
	});
}

function addanexo() {
	console.log('addanexo');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('anexo created successfully');
			$('#btnDelete').show();
			$('#anexoId').val(data.codigo);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addanexo error: ' + textStatus);
		}
	});
}

function updateanexo() {
	console.log('updateanexo');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#anexoId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('anexo updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateanexo error: ' + textStatus);
		}
	});
}

function deleteanexo() {
	console.log('deleteanexo');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#anexoId').val(),
		success: function(data, textStatus, jqXHR){
			alert('anexo deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteanexo error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#anexoList li').remove();
	$.each(list, function(index, anexo) {
		$('#anexoList').append('<li><a href="#" data-identity="' + anexo.codigo + '">'+anexo.nome+'</a></li>');
	});
}

function renderDetails(anexo) {
 	$('#anexoId').val(anexo.codigo);
	$('#nome').val(anexo.nome);
	$('#caminhoArquivo').val(anexo.caminhoArquivo);
	$('#tipoArquivo').val(anexo.tipoArquivo);
	$('#descricaoArquivo').val(anexo.descricaoArquivo);
	$('#tamanhoArquivo').val(anexo.tamanhoArquivo);
	$('#codigoConteudo').val(anexo.codigoConteudo);
	$('#codigoTrabalho').val(anexo.codigoTrabalho);
	$('#codigoProva').val(anexo.codigoProva);
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var anexoId = $('#anexoId').val();
	return JSON.stringify({
		"codigo": anexoId == "" ? null : anexoId, 
		"nome": $('#nome').val(),
		"caminhoArquivo": $('#caminhoArquivo').val(),
		"tipoArquivo": $('#tipoArquivo').val(),
		"descricaoArquivo": $('#descricaoArquivo').val(),
		"tamanhoArquivo": $('#tamanhoArquivo').val(),
		"codigoConteudo": $('#codigoConteudo').val(),
		"codigoTrabalho": $('#codigoTrabalho').val(),
		"codigoProva": $('#codigoProva').val() 
		});
}
