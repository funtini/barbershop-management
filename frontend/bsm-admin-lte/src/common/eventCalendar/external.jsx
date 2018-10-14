import React, { Component } from 'react'

export default class External extends React.Component {
    render() {
      return (
          <div id="external-events" style={{borderTop:'1px solid lightgrey', paddingTop:'0.7em', }}>
          <div className="external-event bg-green">Lunch</div>
          <div className="external-event bg-yellow">Go home</div>
          <div className="external-event bg-aqua">Do homework</div>
          <div className="external-event bg-light-blue">Work on UI design</div>
          <div className="external-event bg-red">Sleep tight</div>
          <div className="checkbox" style={{borderTop:'1px solid lightgrey', paddingTop:'1em'}}>
            <label htmlFor="drop-remove">
              <input type="checkbox" defaultChecked id="drop-remove" />
              remove after drop
            </label>
          </div>
          <div>
          <div className="btn-group" style={{width: '100%', marginBottom: 10}}>
                {/*<button type="button" id="color-chooser-btn" class="btn btn-info btn-block dropdown-toggle" data-toggle="dropdown">Color <span class="caret"></span></button>*/}
                <ul className="fc-color-picker" id="color-chooser">
                  <li><a className="text-aqua" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-blue" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-light-blue" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-teal" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-yellow" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-orange" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-green" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-lime" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-red" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-purple" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-fuchsia" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-muted" href="#"><i className="fa fa-square" /></a></li>
                  <li><a className="text-navy" href="#"><i className="fa fa-square" /></a></li>
                </ul>
              </div>
              {/* /btn-group */}
              <div className="input-group">
                <input id="new-event" type="text" className="form-control" placeholder="Customer Name" />
                <div className="input-group-btn">
                  <button id="add-new-event" type="button" className="btn btn-primary btn-flat">Add</button>
                </div>
                {/* /btn-group */}
              </div>
        </div>
        </div>)
    }
    componentDidMount() {
        /* ADDING EVENTS */
        var currColor = '#3c8dbc' //Red by default
        //Color chooser button
        var colorChooser = $('#color-chooser-btn')
        $('#color-chooser > li > a').click(function (e) {
          e.preventDefault()
          //Save color
          currColor = $(this).css('color')
          //Add color effect to button
          $('#add-new-event').css({ 'background-color': currColor, 'border-color': currColor })
        })
        $('#add-new-event').click(function (e) {
          e.preventDefault()
          //Get value and make sure it is not null
          var val = $('#new-event').val()
          if (val.length == 0) {
            return
          }
    
          //Create events
          var event = $('<div />')
          event.css({
            'background-color': currColor,
            'border-color'    : currColor,
            'color'           : '#fff'
          }).addClass('external-event')
          event.html(val)
          $('#external-events').prepend(event)
    
          //Add draggable funtionality
          init_events(event)
    
          //Remove event from text input
          $('#new-event').val('')
        })
        //   $('#external-events .external-event').each(function() {
        //     var eventObject = {
        //         title: $.trim($(this).text()), // use the element's text as the event title
        //     //  stick: true // maintain when user navigates (see docs on the renderEvent method)
        //       }
      
        //       // store the Event Object in the DOM element so we can get to it later
        //       $(this).data('eventObject', eventObject)
  
        //       // make the event draggable using jQuery UI
        //       $(this).draggable({
        //           zIndex: 999,
        //           revert: true,      // will cause the event to go back to its
        //           revertDuration: 0  //  original position after the drag
        //       });
        //   });

         /* initialize the external events
             -----------------------------------------------------------------*/
             function init_events(ele) {
                ele.each(function () {
          
                  // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
                  // it doesn't need to have a start or end
                  var eventObject = {
                    title: $.trim($(this).text()) // use the element's text as the event title
                  }
          
                  // store the Event Object in the DOM element so we can get to it later
                  $(this).data('eventObject', eventObject)
          
                  // make the event draggable using jQuery UI
                  $(this).draggable({
                    zIndex        : 1070,
                    revert        : true, // will cause the event to go back to its
                    revertDuration: 0  //  original position after the drag
                  })
          
                })
              }
          
              init_events($('#external-events div.external-event'))
    }
  }