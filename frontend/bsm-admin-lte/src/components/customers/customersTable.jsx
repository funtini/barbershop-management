import React, { Component } from 'React'
import Grid from '../../common/layout/grid'

//Components
import EditableButtons from './editableButtons'

//Style
import './customersTable.css';


export default class CustomersTable extends Component {
    constructor(props) {
        super(props);
        this.state = {
            header: [
            { key: 'edit', label: 'Edit' },
            { key: 'name', label: 'Name' },
            { key: 'age', label: 'Age' },
            { key: 'phone', label: 'Phone' },
            { key: 'location', label: 'Location' },

            ],

            data: [
                {
                    id: 1,
                    edit: false,
                    name: 'João Silva',
                    phone: '914043232',
                    age: 44,
                    location: 'Mangualde'
                },
                {
                    id: 2,
                    edit: false,
                    name: 'Pedro Tavares',
                    phone: '963643771',
                    age: 34,
                    location: 'Mangualde'
                },
                {
                    id: 3,
                    edit: false,
                    name: 'Vitor Dias',
                    phone: '916654210',
                    age: 29,
                    location: 'Nelas'
                },
                {
                    id: 4,
                    edit: false,
                    name: 'Ruben Marques',
                    phone: '916533235',
                    age: 25,
                    location: 'Mangualde'
                },
                {
                    id: 5,
                    edit: false,
                    name: 'Pedro Nuno',
                    phone: '914421700',
                    age: 18,
                    location: 'Mangualde'
                },
                {
                    id: 6,
                    edit: false,
                    name: 'Rogerio Matias',
                    phone: '964112755',
                    age: 55,
                    location: 'Penalva'
                },
                {
                    id: 7,
                    edit: false,
                    name: 'Antonio Luis',
                    phone:  '916662721',
                    age: 15,
                    location: 'Mesquitela'
                },
                {
                    id: 8,
                    edit: false,
                    name: 'Joao Luis',
                    phone: '960362521',
                    age: 19,
                    location: 'Mangualde'
                },
                {
                    id: 9,
                    edit: false,
                    name: 'Miguel Dias',
                    phone: '910062799',
                    age: 22,
                    location: 'Nelas'
                },
                {
                    id: 10,
                    edit: false,
                    name: 'Andre Pereira',
                    phone: '914472090',
                    age: 25,
                    location: 'Chãs Tavares'
                },
                {
                    id: 11,
                    edit: false,
                    name: 'Mauricio Dias',
                    phone: '964772990',
                    age: 35,
                    location: 'Viseu'
                },
                {
                    id: 12,
                    edit: false,
                    name: 'Paulo Andrade',
                    phone:  '914552955',
                    age: 27,
                    location: 'Mangualde'
                }
            ]
        }

        this.generateHeaders = this.generateHeaders.bind(this);
        this.generateRows = this.generateRows.bind(this);
        this.handleEditClick = this.handleEditClick.bind(this);
        this.inputName = React.createRef();
        this.inputAge = React.createRef();
        this.inputPhone = React.createRef();
        this.inputLocation = React.createRef();
    }

    componentDidMount() {
        $(function () {
            $('#example1').DataTable(
            //     {
            //   'paging'      : true,
            //   'lengthChange': true,
            //   'searching'   : true,
            //   'ordering'    : true,
            //   'info'        : true,
            //   'autoWidth'   : false
            // }
        )
          })
    }

    generateHeaders() {
        return (
            <tr>
                {this.state.header.map((header) => (
                    <th className="customersTable--header text-center" key={header.key}>
                        {header.label}
                    </th>
                ))}
            </tr>
        )
    }

    generateRows() {
        return (
            this.state.data.map((row, index) => (
                <tr className="customersTable--body text-center" key={row.id}>
                    <td>
                        <EditableButtons
                        onEditClick={() => this.handleEditClick(index)} 
                        onSaveClick={() =>{this.handleEditClick(index),this.handleSaveClick(row)}} 
                        onRemoveClick={() => {this.handleEditClick(index),this.handleRemoveClick(row)}}
                        /></td>
                    <td >{
                        row.edit ?
                            <input
                                ref={this.inputName}
                                className="form-control input-text text-center"
                                type="text"
                                defaultValue={row.name}
                                id="name"
                                name="name"  />
                            :
                            row.name
                    }
                    </td>
                    <td>{
                        row.edit ?

                        <input ref={this.inputAge} className="form-control input-text text-center" type="text" defaultValue={row.age} id="age"
                        name="age"  />
                    :
                    row.age
                    }
                    </td>
                    <td>{
                        row.edit === false ?
                            <strong className="text-muted"><i className={`fa fa-phone`}/> {row.phone}</strong>
                            :
                            <input ref={this.inputPhone} className="form-control input-text text-center" type="text" defaultValue={row.phone} id="age"
                        name="phone"  />
                    }
                    </td>
                    <td>{
                        row.edit === false ?
                            <span>{row.location}</span>
                            :
                            <input ref={this.inputLocation} className="form-control input-text text-center" type="text" defaultValue={row.location} id="age"
                        name="location"  />
                    }
                    </td>
                </tr>
            ))
        )
    }

    handleEditClick = (e) => {
        const newData = this.state.data;
        newData[e].edit = !this.state.data[e].edit;
        this.setState({ data: newData })
        console.log('HIDE/SHOW actions...'+e)
    }

    handleSaveClick = (e) => {
        console.log('SAVED this item')
        console.log(e)
    }

    handleRemoveClick = (e) => {
        console.log('REMOVED this item')
        console.log(e)
    }


    render() {

        var headerComponents = this.generateHeaders();
        var rowComponents = this.generateRows();

        return (this.props.cols ?
            <Grid cols={this.props.cols}>
                <table id="example1" className={`table ${this.props.hover ? 'table-hover' : ''} ${this.props.striped ? 'table-striped' : ''}
              ${this.props.bordered ? 'table-bordered' : ''} table-condensed`}>
                    <thead>
                        {headerComponents}
                    </thead>
                    <tbody>
                        {rowComponents}
                    </tbody>
                </table>
            </Grid> : <table id="example1" className={`table ${this.props.hover ? 'table-hover' : ''} ${this.props.striped ? 'table-striped' : ''}
              ${this.props.bordered ? 'table-bordered' : ''} ${this.props.condensed ? 'table-condensed' : ''}`}>
                <thead>
                    {headerComponents}
                </thead>
                <tbody>
                    {rowComponents}
                </tbody>
            </table>
        )
    }
}