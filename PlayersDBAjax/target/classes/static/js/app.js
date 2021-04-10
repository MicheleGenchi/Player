function formToJSON ( form ) {
    var obj = {};
  
    form.find( "input, select, textarea" ).each(function() {
		var $input = $( this );
		var name = $input.attr( "name" );
		var value = $input.val();
		var v;

    	if (name!="") {
	
		    if( $input.hasClass( "serialize-array" ) ) {
	            if( value.indexOf( "," ) !== -1 ) {
	                v = value.split( "," );
	            } else {
	                v = ( value.length > 0 ) ? [value] : [];
	            }
	        } else {
	            v = value;
	        }
	
	        if( $input.hasClass( "serialize-number" ) ) {
	            v = parseInt( value, 10 );
	        }
	
	        if( $input.hasClass( "serialize-date" ) ) {
	            var elDate = Date.parse( value );
	            if( !isNaN( elDate ) ) {
	                v = new Date( elDate );
	            } else {
	                v = new Date();
	            }
	        }
	
	        if( typeof name !== "undefined"  ) {
	          
	           obj[name] = v;
	          
	        }
    	}
     });

     return obj;
};

function getResources(url) {
	$.get(url, function(dati, status) {
		if (status == "success") {
			caricaTabella(dati.giocatori);
		} else {
			console.log("Metodo getResources()\nNon é possibile recuperare la risorsa\n"+url);
		}
	});
};

function selezionaGiocatore(giocatoreID) {
	var nome, cognome, squadra;
	var url="/players/trova/"+giocatoreID;
	$.get(url, function(dati, status) {
		if (status == "success") {
			$('#id').val(dati.id);
			$('#nome').val(dati.nome);
			$('#cognome').val(dati.cognome);
			$('#squadra').val(dati.squadra);
			$('#btnInvio').hide();
			$('#btnUpdate').show();
		}
	});
};
	
function submitDelete(url) {
	 var deferred=$.Deferred();
	  // Invia il DELETE
	  $.ajax({
	    url: url,
	    type: "DELETE",
	    success: function (msg) {
    		console.log(msg);
    		deferred.resolve();
	    },
	    error : function(msg) {
	    	console.log(msg);
	        deferred.reject('Fallita cancellazione del giocatore');
	    }
	  });
	  return deferred.promise();
};
	
function submitUpdate(url) {
    formData = JSON.stringify(formToJSON($('#modulo')));
    var deferred=$.Deferred();
    $.ajax({
        type: 'PUT', 
        contentType:'application/json',
        dataType:'json',
        url: url, 
        data: formData,
		  success: function(result,status){
			  console.log("submitUpdate  result value = "+result.cognome+" "+result.nome);
			  console.log("submitUpdate  status value = "+status);
		      if (status == "success") {
		    	    deferred.resolve();
		    		console.log("Metodo submitUpdate()\nAggiornamento riuscito");
			      }
			      else {
			    	deferred.reject('Fallito aggiornamento del giocatore');
			    	console.log("errore nella cifratura del json\n"+formData);  
			      }
		  }
	});
    return deferred.promise();
};

function postData(url) {
	formData = JSON.stringify(formToJSON($('#modulo')));
    var deferred=$.Deferred();
    $.ajax({
        type: 'POST', 
        dataType: "text",
        contentType: "application/json; charset=utf-8",
        url: url, 
        data: formData,
        success: function(data) {
        	console.log('postData done :'+data);
    		deferred.resolve(data);
        },
        error: function(xhr){
    		console.log('postData fail:' + xhr.responseText);
    		console.log('postData statusCode :' + xhr.status);
        	deferred.reject(xhr.responseText);
        }
    });
    return deferred.promise();
};

	
function inviaForm() {
    var deferred = $.Deferred();
    var url="http://localhost:8080/players/new";
    $.when(postData(url)).done(function() {	
    	console.log('invio form done');
    	getResources('http://localhost:8080/players/lista');
    }).fail(function(value) {
    	alert(value);
    });
    resetForm('#modulo');
    
}


function caricaTabella(dati) {
	$('#tableCalciatori').remove();
	var table = $('<table class="table" id="tableCalciatori"  border=1 cellspacing=0 cellpadding=5 />').appendTo($('#somediv'));
	$('<thead/>').appendTo(table)
		.append("<tr>")
		.append($('<th width=50 />').text('ID'))
		.append($('<th width=200 />').text("NOME"))
		.append($('<th width=200 />').text("COGNOME"))
		.append($('<th width=100 />').text("SQUADRA"))
		.append("</tr>")
	
	$('<tbody>').appendTo(table);
	$(dati).each(function(i,giocatore) {
		$('<tr/>').appendTo(table)
		.append($('<th scope="row" width=50 />').text(giocatore.id))
		.append($('<td width=200 />').text(giocatore.nome))
		.append($('<td width=200 />').text(giocatore.cognome))
		.append($('<td width=100 />').text(giocatore.squadra))
		.append($('<td width=100 />').html('<button id="btnSeleziona" onclick="selezionaGiocatore('+giocatore.id+')">SELEZIONA</button>'));
	});
	$('</tbody>').appendTo(table);
}
	
	
function resetForm(form) {
  $(':input', form).each(function() {
    var type = this.type;
    var tag = this.tagName.toLowerCase();
    if (type == 'text' || type == 'password' || tag == 'textarea')
      this.value = "";
    else if (type == 'checkbox' || type == 'radio')
      this.checked = false;
    else if (tag == 'select')
      this.selectedIndex = -1;
  });
};



$().ready(function() {
	$('#modulo').validate({
		rules : {
			nome : {
				required:true
			},
			cognome : {
				required:true
			}
		},
		messages : {
			nome : "il nome deve essere digitato",
			cognome : "il cognome deve essere digitato"
		},
		submitHandler: function() {
            inviaForm();
        }
	});
	
	$('#btnSvuota').click(function() {
		resetForm("#modulo");
		$('#btnUpdate').hide();
		$('#btnInvio').show();
		$('#somediv').empty();
	});

	$('#bntLista').click(function() {
		getResources('http://localhost:8080/players/lista');
	});
	
	
	$('#btnDelete').click(function() {
		var id=prompt("Quale giocatore vuoi cancellare. Inserire il suo ID?");
		var url="http://localhost:8080/players/delete/"+id;
	    var deferred = $.Deferred();
	    
	    $.when(submitDelete(url)).done(function() {
	    	getResources('http://localhost:8080/players/lista');
	    }).fail(function(value) {
	    	alert(value);
	    });
	});
	
	$('#btnUpdate').click(function() {
		var url="http://localhost:8080/players/aggiorna/"+$('#id').val();
	    var deferred = $.Deferred();
	    $.when(submitUpdate(url)).done(function() {
	    	getResources('http://localhost:8080/players/lista');
	    }).fail(function(value) {
	    	alert(value);
	    });
	    resetForm('#modulo');  
	});
	
});