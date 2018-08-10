import React, { Component } from 'React'
import Grid from '../layout/grid'

export default class ResponsiveTable extends Component {
    constructor(props) {
        super(props);

        this.generateHeaders = this.generateHeaders.bind(this);
        this.generateRows = this.generateRows.bind(this);
    }

    generateHeaders() {
        return (
            <tr>
                {this.props.column.map((header) => (
                    <th key={header.key}>
                        {header.label}
                    </th>
                ))}
            </tr>
        )
    }

    generateRows() {
        return (
            this.props.data.map((row) => (
                <tr key={row.id}>
                    <td>{row.id}</td>
                    <td>{row.user}</td>
                    <td>{row.date}</td>
                    <td>{row.status}</td>
                    <td>{row.reason}</td>
                </tr>
            ))
        )
    }


    render() {
        var headerComponents = this.generateHeaders();
        var rowComponents = this.generateRows();

        return (
            <Grid cols={this.props.cols}>
                <div className="box">
                    <div className="box-header">
                        <h3 className="box-title">{this.props.title}</h3>
                        {
                            this.props.searchBox ?
                                <div className="box-tools">
                                    <div className="input-group input-group-sm" style={{ width: '150px' }}>
                                        <input type="text" name="table_search" className="form-control pull-right" placeholder="Search" />

                                        <div className="input-group-btn">
                                            <button type="submit" className="btn btn-default"><i className="fa fa-search"></i></button>
                                        </div>
                                    </div>
                                </div>
                                :
                                null
                        }
                    </div>
                    <div className="box-body table-responsive no-padding">
                        <table className="table table-hover">
                            <thead>
                                {headerComponents}
                            </thead>
                            <tbody>
                                {rowComponents}
                            </tbody>
                        </table>
                    </div>

                </div>
            </Grid>
        )
    }
}