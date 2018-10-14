import React, { Component } from 'react'

export default class External extends React.Component {
    render() {
      return (
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
                <input id="new-event" type="text" className="form-control" placeholder="Event Title" />
                <div className="input-group-btn">
                  <button id="add-new-event" type="button" className="btn btn-primary btn-flat">Add</button>
                </div>
                {/* /btn-group */}
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
    }
  }