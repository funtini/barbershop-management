import React, { Component } from 'react'

import RadioOption from './radioOption/radioOption'
import './radioButtonGroup.css'

export default class RadioButtonGroup extends Component {

    state = {
        selectedOption: "opt1",
        switch: true
    }

    handleOptionChange = (event) => {
        console.log(event.target.value)
        this.setState({
            selectedOption: event.target.value
        })
    }

    handleSwitchChange = () => {
        this.setState({
            switch: !this.state.switch
        })
    }

    render() {

        return (
            <div className="RadioGroup">
                <h4>UI Theme:</h4>
                <form>
                    <RadioOption value="opt1" checked={this.state.selectedOption === "opt1"} onChange={this.handleOptionChange}>
                        Blue
                    </RadioOption>
                    <RadioOption value="opt2" checked={this.state.selectedOption === "opt2"} onChange={this.handleOptionChange}>
                        Black
                    </RadioOption>
                    <RadioOption value="opt3" checked={this.state.selectedOption === "opt3"} onChange={this.handleOptionChange}>
                        Green
                    </RadioOption>
                    <label className="switch">
                        <input type="checkbox" checked={this.state.switch === true} onChange={this.handleSwitchChange}/>
                        <span className="slider" />
                    </label>
                    
                </form>
            </div>
        )
    }
}