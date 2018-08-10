import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import Content from '../../common/template/content/content'
import PageHeader from '../../common/template/content/pageHeader'
import Row from '../../common/layout/row'
import Grid from '../../common/layout/grid'
import Box from '../../common/elements/box'
import InputBox from '../../common/elements/inputBox'
import Select from '../../common/elements/select'
import Calendar from '../../common/calendar/calendar'
import DateRangePicker from '../../common/calendar/dateRangePicker'
var moment = require('moment');




export default class Forms extends Component {

 componentWillMount(){
   //Initialize Select2 Elements
   $(".select2").select2();

   //Datemask dd/mm/yyyy$(":input").inputmask();
   $(":input").inputmask();

   $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
   //Datemask2 mm/dd/yyyy
   $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});
   //Money Euro
   $("[data-mask]").inputmask();

   //Date range picker
   $('#reservation').daterangepicker();
   //Date range picker with time picker
   $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'});
   //Date range as a button
   $('.daterange-btn').daterangepicker(
       {
         ranges: {
           'Today': [moment(), moment()],
           'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
         },
         startDate: moment().subtract(29, 'days'),
         endDate: moment()
       },
       function (start, end) {
         $('.daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
       }
   );



   //iCheck for checkbox and radio inputs
   $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
     checkboxClass: 'icheckbox_minimal-blue',
     radioClass: 'iradio_minimal-blue'
   });
   //Red color scheme for iCheck
   $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
     checkboxClass: 'icheckbox_minimal-red',
     radioClass: 'iradio_minimal-red'
   });
   //Flat red color scheme for iCheck
   $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
     checkboxClass: 'icheckbox_flat-green',
     radioClass: 'iradio_flat-green'
   });

   //Colorpicker
   $(".my-colorpicker1").colorpicker();
   //color picker with addon
   $(".my-colorpicker2").colorpicker();

   //Timepicker
   $(".timepicker").timepicker({
     showInputs: false
   });
 }

    render() {

        

        
        return (
            <div>
                <ContentHeader title='Forms' small='version 1.0' />

                <Content>
                    <Row>
                        <Grid cols ="12 6">
                        <Box title="Inputs different sizes" icon="edit" collapsable type="primary" >
                        <InputBox type="text" placeholder="Name" size="lg" />
                        <br/>
                        <Row>
                        <InputBox type="text" placeholder="Morada" size="lg" cols="12 4" />
                        </Row>
                        <br/>
                        <InputBox type="text" placeholder="Name" size="sm" />
                        <br/>
                        <Row>
                        <InputBox type="text" placeholder="Morada" size="sm" cols="12 3" />
                        </Row>
                        <br/>
                        <InputBox type="text" placeholder="Name" />
                        <br/>
                        <Row>
                        <InputBox type="text" placeholder="Morada" cols="12 6" />
                        </Row>
                        <br/>
                        <br/>
                        </Box>
                        <Box title="Mask inputs" icon="edit" collapsable type="primary" >
                        <div className="form-group">
                          <label>Date masks:</label>
                          <div className="input-group">
                            <div className="input-group-addon">
                              <i className="fa fa-calendar" />
                            </div>
                            <input type="text" className="form-control" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask />
                          </div>
                          {/* /.input group */}
                        </div>
                        <div>
                          <div className="form-group">
                            <div className="input-group">
                              <div className="input-group-addon">
                                <i className="fa fa-calendar" />
                              </div>
                              <input type="text" className="form-control" data-inputmask="'alias': 'mm/dd/yyyy'" data-mask />
                            </div>
                            {/* /.input group */}
                          </div>
                          {/* /.form group */}
                          {/* phone mask */}
                          <div className="form-group">
                            <label>US phone mask:</label>
                            <div className="input-group">
                              <div className="input-group-addon">
                                <i className="fa fa-phone" />
                              </div>
                              <input type="text" className="form-control" data-inputmask="&quot;mask&quot;: &quot;(999) 999-9999&quot;" data-mask />
                            </div>
                            {/* /.input group */}
                          </div>
                        </div>
                        </Box>
                        <Box title="Special inputs" icon="edit" collapsable type="primary" >
                        <div className="bootstrap-timepicker">
                          <div className="form-group">
                            <label>Time picker:</label>
                            <div className="input-group">
                              <input type="text" className="form-control timepicker" />
                              <div className="input-group-addon">
                                <i className="fa fa-clock-o" />
                              </div>
                            </div>
                            {/* /.input group */}
                          </div>
                          {/* /.form group */}
                        </div>
                        <div>
        {/* Date */}
        <div className="form-group">
          <label>Date:</label>
          <div className="input-group date">
            <div className="input-group-addon">
              <i className="fa fa-calendar" />
            </div>
            <input type="text" className="form-control pull-right" id="datepicker" />
          </div>
          {/* /.input group */}
        </div>
        {/* /.form group */}
        {/* Date range */}
        <div className="form-group">
          <label>Date range:</label>
          <div className="input-group">
            <div className="input-group-addon">
              <i className="fa fa-calendar" />
            </div>
            <input type="text" className="form-control pull-right" id="reservation" />
          </div>
          {/* /.input group */}
        </div>
        {/* /.form group */}
        {/* Date and time range */}
        <div className="form-group">
          <label>Date and time range:</label>
          <div className="input-group">
            <div className="input-group-addon">
              <i className="fa fa-clock-o" />
            </div>
            <input type="text" className="form-control pull-right" id="reservationtime" />
          </div>
          {/* /.input group */}
        </div>
        {/* /.form group */}
        {/* Date and time range */}
        <div className="form-group">
          <label>Date range button:</label>
          <div className="input-group">
            <button type="button" className="daterange-btn btn btn-default pull-right" id="daterange-btn">
              <span>
                <i className="fa fa-calendar" /> Date range picker
              </span>
              <i className="fa fa-caret-down" />
            </button>
          </div>
        </div>
      </div>

      <Calendar/>
      <DateRangePicker/>
                        </Box>
                        <Box  title="Select" icon="edit" collapsable type="primary" >
                        <Select label="Nacionalidade" width="100">
                            <option selected="selected">Alabama</option>
                            <option>Alaska</option>
                            <option>California</option>
                            <option>Delaware</option>
                            <option>Tennessee</option>
                            <option>Texas</option>
                            <option>Washington</option>
                        </Select>
                        <Select label="Cor" width="50">
                            <option selected="selected">Alabama</option>
                            <option>Alaska</option>
                            <option>California</option>
                            <option>Delaware</option>
                            <option>Tennessee</option>
                            <option>Texas</option>
                            <option>Washington</option>
                        </Select>
                        <Select label="Idade" width="20">
                            <option selected="selected">12</option>
                            <option>55</option>
                            <option>321</option>
                            <option>22</option>
                            <option>11</option>
                            <option>15</option>
                            <option>22</option>
                        </Select>
                        </Box>
                        </Grid>
                        <Grid cols ="12 6">
                        <Box title="General Elements" icon="edit" collapsable type="warning" >
                        <form role="form">
        {/* text input */}
        <div className="form-group">
          <label>Text</label>
          <input type="text" className="form-control" placeholder="Enter ..." />
        </div>
        <div className="form-group">
          <label>Text Disabled</label>
          <input type="text" className="form-control" placeholder="Enter ..." disabled />
        </div>
        {/* textarea */}
        <div className="form-group">
          <label>Textarea</label>
          <textarea className="form-control" rows={3} placeholder="Enter ..." defaultValue={""} />
        </div>
        <div className="form-group">
          <label>Textarea Disabled</label>
          <textarea className="form-control" rows={3} placeholder="Enter ..." disabled defaultValue={""} />
        </div>
        {/* input states */}
        <div className="form-group has-success">
          <label className="control-label" htmlFor="inputSuccess"><i className="fa fa-check" /> Input with success</label>
          <input type="text" className="form-control" id="inputSuccess" placeholder="Enter ..." />
          <span className="help-block">Help block with success</span>
        </div>
        <div className="form-group has-warning">
          <label className="control-label" htmlFor="inputWarning"><i className="fa fa-bell-o" /> Input with
            warning</label>
          <input type="text" className="form-control" id="inputWarning" placeholder="Enter ..." />
          <span className="help-block">Help block with warning</span>
        </div>
        <div className="form-group has-error">
          <label className="control-label" htmlFor="inputError"><i className="fa fa-times-circle-o" /> Input with
            error</label>
          <input type="text" className="form-control" id="inputError" placeholder="Enter ..." />
          <span className="help-block">Help block with error</span>
        </div>
        {/* checkbox */}
        <div className="form-group">
          <div className="checkbox">
            <label>
              <input type="checkbox" />
              Checkbox 1
            </label>
          </div>
          <div className="checkbox">
            <label>
              <input type="checkbox" />
              Checkbox 2
            </label>
          </div>
          <div className="checkbox">
            <label>
              <input type="checkbox" disabled />
              Checkbox disabled
            </label>
          </div>
        </div>
        {/* radio */}
        <div className="form-group">
          <div className="radio">
            <label>
              <input type="radio" name="optionsRadios" id="optionsRadios1" defaultValue="option1" defaultChecked />
              Option one is this and that—be sure to include why it's great
            </label>
          </div>
          <div className="radio">
            <label>
              <input type="radio" name="optionsRadios" id="optionsRadios2" defaultValue="option2" />
              Option two can be something else and selecting it will deselect option one
            </label>
          </div>
          <div className="radio">
            <label>
              <input type="radio" name="optionsRadios" id="optionsRadios3" defaultValue="option3" disabled />
              Option three is disabled
            </label>
          </div>
        </div>
        {/* select */}
        <div className="form-group">
          <label>Select</label>
          <select className="form-control">
            <option>option 1</option>
            <option>option 2</option>
            <option>option 3</option>
            <option>option 4</option>
            <option>option 5</option>
          </select>
        </div>
        <div className="form-group">
          <label>Select Disabled</label>
          <select className="form-control" disabled>
            <option>option 1</option>
            <option>option 2</option>
            <option>option 3</option>
            <option>option 4</option>
            <option>option 5</option>
          </select>
        </div>
        {/* Select multiple*/}
        <div className="form-group">
          <label>Select Multiple</label>
          <select multiple className="form-control">
            <option>option 1</option>
            <option>option 2</option>
            <option>option 3</option>
            <option>option 4</option>
            <option>option 5</option>
          </select>
        </div>
        <div className="form-group">
          <label>Select Multiple Disabled</label>
          <select multiple className="form-control" disabled>
            <option>option 1</option>
            <option>option 2</option>
            <option>option 3</option>
            <option>option 4</option>
            <option>option 5</option>
          </select>
        </div>
      </form>
                        </Box>
                        </Grid>
                       
                    </Row>     
                    <PageHeader title="Forms" />
                    <PageHeader title="More" />
                    
                    
                    
                
                   
                    <PageHeader />


                </Content>
            </div >
        )
    }
}