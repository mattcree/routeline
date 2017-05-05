    var stations;
    var idFrom;
    var idTo;
    var nameFrom;
    var nameTo;
    var avoidVia;
    var options
    // takes an array of Objects in the following form
    // {"id":1,"name":"York","line":"Red"}
    // and returns a matcher function that checks whether a given string is found in the "name" of any of the Objects in the array
    var substringMatcher = function(objs) {
        return function findMatches(q, cb) {

        console.log('trying to match:');
        console.log(q);
        var matches, substringRegex;

        // an array that will be populated with substring matches
        matches = [];

        // regex used to determine if a string contains the substring `q`
        substrRegex = new RegExp(q, 'i');

        // iterate through the pool of Objects and for any Object that's name
        // contains the substring `q`, add the name-string to the `matches` array
            $.each(objs, function(i, obj) {
                if (substrRegex.test(obj)) {
                    matches.push(obj);
                }
            });

            cb(matches);
        };
    };


    //get the list of stations
    $.get('/api/stations').then(function(data){
        stations = data;

        // stations should be an array of strings
        console.log(stations);

        //settings for jQuery typeahead plugin
        var typeaheadCustomSettings = {
            hint: true,
            highlight: true,
            minLength: 1
        };

        // data source for our jQuery typeahead
        var dataSetup = {
            name: 'stations',
            source: substringMatcher(stations),
            display: function(obj){
                return obj;
            }
        };

        var stationFromInput = $('#stationFromInput');
        console.log(stationFromInput);
        // activate the jQuery typeahead plugin on the element with id "stationFromInput"
        stationFromInput.typeahead(typeaheadCustomSettings, dataSetup);

        var stationToInput = $('#stationToInput');
        console.log(stationToInput);
        // activate the jQuery typeahead plugin on the element with id "stationToInput"
        stationToInput.typeahead(typeaheadCustomSettings, dataSetup);

        var stationAvoidViaInput = $('#stationAvoidViaInput');
        console.log(stationAvoidViaInput);
        // activate the jQuery typeahead plugin on the element with id "stationToInput"
        stationAvoidViaInput.typeahead(typeaheadCustomSettings, dataSetup);
    });


    $('#stationFromInput').bind('typeahead:autocomplete typeahead:select', function(ev, suggestion) {
        console.log('Selection: ' + suggestion.id);
        console.log('suggestion: ' + suggestion.name);
        idFrom = suggestion.id;
        nameFrom = suggestion;
    });

    $('#stationToInput').bind('typeahead:autocomplete typeahead:select', function(ev, suggestion) {
        console.log('Selection: ' + suggestion.id);
        console.log('suggestion: ' + suggestion.name);
        idTo = suggestion.id;
        nameTo = suggestion;
    });

    $('#stationAvoidViaInput').bind('typeahead:autocomplete typeahead:select', function(ev, suggestion) {
        console.log('Selection: ' + suggestion.id);
        console.log('suggestion: ' + suggestion.name);
        idTo = suggestion.id;
        avoidVia = suggestion;
    });




    function getRouteData(nameFrom, nameTo, options, avoidVia) {
        var routeApiUrl;

        if (options == "") {
            var routeApiUrl = '/api/route/names/' + nameFrom + '/' + nameTo;
        } else {
            var routeApiUrl = '/api/route/names/' + nameFrom + '/' + nameTo + options + avoidVia;
        }

        $.get(routeApiUrl).then(function(data){

            var firstName = data.route[0].name;
            var firstLine = data.route[0].line;
            var hours = Math.floor(data.timeInMinutes / 60);
            var minutes = data.timeInMinutes % 60;
            var totalTime = '';
            var expandedDetails = '';
            var details = '';
            var summary = '';

            if (hours < 1){
                totalTime = data.timeInMinutes + ' minutes';
            } else {
                totalTime = hours + ' hour(s) ' + minutes + ' minutes';
            }

            summary += '<h4>Journey Summary: </h4><p>From '+data.start.name+' to '+data.destination.name+'</p><hr>'


            if (data.numberOfChanges > 0) {
                summary += '<p>'+data.numberOfChanges+' change(s)</p>';
                summary += '<ul>';
                for(var i = 0; i < data.changes.length;i++) {
                    summary += '<li>at '+data.changes[i].stopA.name +' to '+ data.changes[i].stopB.line +' line</li>';
                }
            }
            summary += '</ul>';
            summary += '<p>Duration: '+totalTime+'</p>';

            details += '<hr><b>Start: '+firstName+' on '+firstLine+'</b><br>';
            details += '<span class="glyphicon glyphicon-arrow-down"></span><br>';

            for (var i = 1; i < data.route.length; i++) {
                var name = data.route[i].name;
                var line = data.route[i].line;

                if(i+1 != data.route.length) {
                    details += '<p>'+name+' on '+line+'</p>';
                    details += '<span class="glyphicon glyphicon-arrow-down"></span><br>';
                } else {
                    details += '<b>Destination: '+name+'</b><hr>';
                }
            }

            details += '<h4>Journey duration: '+totalTime+'</h4>';
            expandedDetails +='<details>'+details+'</details>';

            console.log(details);
            console.log(expandedDetails);

            $('#route-results').empty()

            $('#route-results').append(
                '<div class="panel container-fluid">'+summary+expandedDetails+'</div>'
            )

        }).fail(function() {
              $('#route-results').empty();
              $('#route-results').append('<div class="panel container-fluid"><h4>No route found with your currently selected options.</h4><p><b>Note: </b>Some stations cannot be avoided.</p></div>');
        });
    }



    $('#goButton').click(function() {
        getRouteData(nameFrom, nameTo, $('#viaAvoidOptions').val(), avoidVia);
    });




