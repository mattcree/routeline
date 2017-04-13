$ ->
    $.get "/api/stops", (stops) ->
        $.each stops, (index, stop) ->
            $('#stops').append $("<li>").text stop.name