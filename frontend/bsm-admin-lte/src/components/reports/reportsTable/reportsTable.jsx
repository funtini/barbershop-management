import React, { Component } from 'React'
import Grid from '../../../common/layout/grid'

//Style
import './ReportsTable.css'

//Elements
import ColoredLabel from '../../../common/label/coloredLabel'
import Tooltip from '../../../common/ui/tooltip'


export default class ReportsTable extends Component {
    constructor(props) {
        super(props);
        this.state = {
            header: [{ key: 'year', label: 'Year' },
            { key: 'month', label: 'Month' },
            { key: 'profit', label: 'Profit' },
            { key: 'status', label: 'Status' },

            ],

            data: [
                {
                    id: 1,
                    year: 2018,
                    month: 'Janeiro',
                    profit: 1132,
                    status: true,
                },
                {
                    id: 2,
                    year: 2018,
                    month: 'Fevereiro',
                    profit: 1382,
                    status: true,
                },
                {
                    id: 3,
                    year: 2018,
                    month: 'Março',
                    profit: 1435,
                    status: true,
                },
                {
                    id: 4,
                    year: 2018,
                    month: 'Abril',
                    profit: 1565,
                    status: true,
                },
                {
                    id: 5,
                    year: 2018,
                    month: 'Maio',
                    profit: 1340,
                    status: true,
                }
            ],
            expandedRows: []

        }

        this.generateHeaders = this.generateHeaders.bind(this);
        // this.generateRows = this.generateRows.bind(this);

    }

    handleRowClick(rowId) {
        const currentExpandedRows = this.state.expandedRows;
        const isRowCurrentlyExpanded = currentExpandedRows.includes(rowId);

        const newExpandedRows = isRowCurrentlyExpanded ?
            currentExpandedRows.filter(id => id !== rowId) :
            currentExpandedRows.concat(rowId);

        this.setState({ expandedRows: newExpandedRows });
    }

    renderItem(item) {
        const clickCallback = () => this.handleRowClick(item.id);
        const itemRows = [
            <tr className="row_standart text-center" onClick={clickCallback} key={"row-data-" + item.id} >
           
                <td>
                    {item.year}
                </td>
                <td >
                    {item.month}
                </td>
                <td>
                    <ColoredLabel type="success" text={`${item.profit} €`} />
                </td>
                <td>
                    <strong className="text-muted">
                        {item.status ? <ColoredLabel type="default" text="closed" /> : <ColoredLabel type="default" text="open" />}
                    </strong>
                </td>
                <td>
                <i className="fa fa-search-plus icon_expand"/>
                </td>
            </tr>
        ];

        if (this.state.expandedRows.includes(item.id)) {
            itemRows.push(
                <tr key={"row-expanded-" + item.id} >
                <td colSpan={5}>
                <div className="row_expandable" >
                    
                    <p><strong>Income:</strong> 1500€</p>
                    <p><strong>Expenses:</strong> 345€</p>
                    <p><strong>Roi:</strong> 255%</p>
                    <p><strong>New Customers:</strong> +16</p>
                    <p><strong>Open Business Days:</strong> 22 dias</p>
                    <div className="row_description_card">
                    <div className="row_description_card--header bg-brown-light ">
                        Daily Profit
                        </div>
                        <div className="row_description_card--body bg-yellow-primary">
                        <Tooltip 
                    position='bottom' 
                    message={'Represents the average profit realized each worked day'}>
                        77€
                        </Tooltip>
                        </div>  
                    </div>
                    
                    <div className="row_description_card second_card">
                    
                    <div className="row_description_card--header bg-green-primary">
                        Growth
                        </div>
                       
                        <div className="row_description_card--body bg-green-light">
                        <Tooltip 
                    position='bottom' 
                    message={'Represents the evolution of income over last month.'}>
                        +5%
                        </Tooltip>
                        </div>  
                        
                    </div>  
               
                 </div>
                 </td>
                </tr>
            );
        }
        return itemRows;
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

    renderRows() {
        let allItemRows = [];

        this.state.data.map(row => {
            const itemRow = this.renderItem(row);
            allItemRows = allItemRows.concat(itemRow);

        })
        
        return allItemRows;
        // this.state.data.forEach(item => {
        //     const perItemRows = this.renderItem(item);
        //     allItemRows = allItemRows.concat(perItemRows);
        // });
    }

    // generateRows() {
    //     return (
    //         this.state.data.map((row, index) => (
    //             <tr className="text-center panel-collapse" key={row.id}>

    //                 <td>
    //                     {row.year}
    //                 </td>
    //                 <td >
    //                     {row.month}
    //                 </td>
    //                 <td>
    //                     <ColoredLabel type="success" text={`${row.profit} €`} />
    //                 </td>
    //                 <td>
    //                     <strong className="text-muted">
    //                         {row.status ? <ColoredLabel type="default" text="closed" /> : <ColoredLabel type="default" text="open" />}
    //                     </strong>
    //                 </td>
    //             </tr>
    //         ))
    //     )
    // }

    render() {

        var headerComponents = this.generateHeaders();
        var rowComponents = this.renderRows();

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