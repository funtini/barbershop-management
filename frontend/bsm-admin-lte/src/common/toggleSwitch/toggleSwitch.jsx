import React, { Component } from 'react'

import './toggleSwitch.css'

export default class ToggleSwitch extends Component {

    state = {
        selectedOption: "opt1",
        switch: true
    }

    handleSwitchChange = () => {
        this.setState({
            switch: !this.state.switch
        })
    }

    render() {

        return (
            <div className="toggleSwitch">
                <div>
                    <h4>Toggle Switch</h4>
                    <label className="switch">
                        <input type="checkbox" />
                        <span className="slider" />
                    </label>
                    <label className="switch">
                        <input type="checkbox" defaultChecked />
                        <span className="slider" />
                    </label><br /><br />
                    <label className="switch">
                        <input type="checkbox" />
                        <span className="slider round" />
                    </label>
                    <label className="switch">
                        <input type="checkbox" defaultChecked />
                        <span className="slider round" />
                    </label>
                </div>
            </div>
        )
    }
}