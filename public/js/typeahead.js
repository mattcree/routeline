    /*!
     * Derived from Bootstrap 'Twitter Typeahead'
     * Copyright 2013 Twitter, Inc.
     * Licensed under the MIT License
     *
     */

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
        var optionsText;

        if (options == "") {
            optionsText = "";
            var routeApiUrl = '/api/route/names/' + nameFrom + '/' + nameTo;
        } else {
            optionsText = options.substring(1, options.length-1) + " " + avoidVia;
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
            var start;
            var destination;

            if (hours < 1){
                totalTime = data.timeInMinutes + ' minutes';
            } else {
                totalTime = hours + ' hour(s) ' + minutes + ' minutes';
            }

            start = data.start;
            destination = data.destination;
            summary += '<h4>Journey Summary: </h4><p>From '+start.name+' to '+destination.name+' '+optionsText+'</p>'


            if (data.numberOfChanges > 0) {
                summary += '<p>'+data.numberOfChanges+' change(s)</p>';
                summary += '<ul>';
                for(var i = 0; i < data.changes.length;i++) {
                    summary += '<li>at '+data.changes[i].stopA.name +' to '+ data.changes[i].stopB.line +' line</li>';
                }
            }
            summary += '</ul>';
            summary += '<p>Duration: '+totalTime+'</p>';
            details += '<hr>'
            details += '<ul class = "displayroute"><li><b>Start: '+firstName+' on '+firstLine+'</b></li>';
            details += '<li class="times">'+data.connections[0].time+' minutes</li>'
            for (var i = 1; i < data.connections.length; i++) {
                var connection = data.connections[i];
                var name = connection.stopA.name;
                var line = connection.stopA.line;
                var time = connection.time;

                details += '<li>'+name+' on '+line+'</li>';
                if (connection.lineChange) {
                    details += '<li class = "changes">Change to '+connection.stopB.line+', transfer time '+ time +' minutes</li>';
                } else {
                    details += '<li class = "times">'+ time +' minutes</li>';
                }


            }
            details += '<li class = "destination"><b>Destination: '+destination.name+'</b></li>';
            details += '</ul><hr>'
            details += '<h4>Journey duration: '+totalTime+'</h4>';
            expandedDetails +='<details id = "details">'+details+'</details>';

            console.log(details);
            console.log(expandedDetails);

            $('#route-results').empty()

            $('#route-results').append(
                '<div class="panel container-fluid">'+summary+expandedDetails+'</div>'
            )

        }).fail(function() {
              $('#route-results').empty();
              $('#route-results').append('<div class="panel container-fluid" id="no-route"><h4>No route found with your currently selected options.</h4><p><b>Note: </b>Some stations cannot be avoided.</p></div>');
        });
    }



    $('#goButton').click(function() {
        console.log(nameFrom);
        console.log(nameTo);
        getRouteData(nameFrom, nameTo, $('#viaAvoidOptions').val(), avoidVia);
        nameFrom = '';
        nameTo = '';
        avoidVia = '';
        $('#stationFromInput').val("");
        $('#stationToInput').val("");
        $('#stationAvoidViaInput').val("");
    });




