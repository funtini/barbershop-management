import React, { Component } from 'React'
import Grid from '../../../common/layout/grid'
import './ReportsTable.css'
import EditableButtons from './editableButtons'
import ColoredLabel from '../../../common/label/coloredLabel'


export default class ReportsTable extends Component {
    constructor(props) {
        super(props);
        this.state = {
            header: [{ key: 'edit', label: ' ' },
            { key: 'name', label: 'Name' },
            { key: 'type', label: 'Type' },
            { key: 'price', label: 'Price' },

            ],

            data: [
                {
                    id: 1,
                    edit: false,
                    name: 'Corte Simples',
                    type: <ColoredLabel type="success" text="Haircut" />,
                    price: 8
                },
                {
                    id: 2,
                    edit: false,
                    name: 'Corte Completo',
                    type: <ColoredLabel type="success" text="Haircut" />,
                    price: 10
                },
                {
                    id: 3,
                    edit: false,
                    name: 'Barba Curta',
                    type: <ColoredLabel type="warning" text="Shave" />,
                    price: 4
                },
                {
                    id: 4,
                    edit: false,
                    name: 'Barba Longa',
                    type: <ColoredLabel type="warning" text="Shave" />,
                    price: 6
                },
                {
                    id: 5,
                    edit: false,
                    name: 'BarberShave',
                    type: <ColoredLabel type="primary" text="Extra" />,
                    price: 14
                }
            ]
        }

        this.generateHeaders = this.generateHeaders.bind(this);
        this.generateRows = this.generateRows.bind(this);
        this.handleEditClick = this.handleEditClick.bind(this);
        this.inputName = React.createRef();
        this.inputType = React.createRef();
        this.inputPrice = React.createRef();
    }

    generateHeaders() {
        return (
            <tr>
                {this.state.header.map((header) => (
                    <th className="text-center" key={header.key}>
                        {header.label}
                    </th>
                ))}
            </tr>
        )
    }

    generateRows() {
        return (
            this.state.data.map((row, index) => (
                <tr className="text-center" key={row.id}>
                    <td>
                        <EditableButtons 
                        onEditClick={() => this.handleEditClick(index)} 
                        onSaveClick={() =>{this.handleEditClick(index),this.handleSaveClick(row)}} 
                        onRemoveClick={() => {this.handleEditClick(index),this.handleRemoveClick(row)}}
                        /></td>
                    <td >{
                        row.edit ?
                            <input ref={this.inputName} className="form-control input-text" type="text" defaultValue={row.name} id="name"
                                name="name" style={{ width: "70%", textAlign: "center", display: 'inline-block', marginLeft: '1em' }} />
                            :
                            row.name
                    }
                    </td>
                    <td>{
                        row.edit ?

                            <select ref={this.inputType} onChange={this.onChange} className="form-control select2"
                                style={{ width: `100%`, display: 'inline-block' }}>
                                <option defaultChecked>Haircut</option>
                                <option>Shave</option>
                                <option>Extra</option>
                            </select>
                            :
                            row.type
                    }
                    </td>
                    <td>{
                        row.edit === false ?
                            <strong className="text-muted"> {row.price} €</strong>
                            :
                            <input ref={this.inputPrice} className="form-control input-number" type="number" defaultValue={row.price}
                                id="price" min="0" max="100" name="price" step="0.1" style={{ width: "50%", textAlign: "center", display: 'block', marginLeft: '3em' }} />
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
                <table className={`table ${this.props.hover ? 'table-hover' : ''} ${this.props.striped ? 'table-striped' : ''}
              ${this.props.bordered ? 'table-bordered' : ''} table-condensed`}>
                    <thead>
                        {headerComponents}
                    </thead>
                    <tbody>
                        {rowComponents}
                    </tbody>
                </table>
            </Grid> : <table className={`table ${this.props.hover ? 'table-hover' : ''} ${this.props.striped ? 'table-striped' : ''}
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