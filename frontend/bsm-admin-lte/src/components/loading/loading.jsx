import React, { Component } from 'react'

export default class Loading extends Component {
    render() {
        return (
            <div className="text-center" style={{ paddingTop: "2em" }}>
                <i className="fa fa-spinner fa-spin fa-3x fa-fw"></i>
            </div>
        )
    }
}