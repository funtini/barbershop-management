import React, { Component } from 'React'
import Button from '../../common/elements/button'

export default class EditableButtons extends Component {
    constructor(props) {
        super(props);
        this.state={
            edit:true
        }
        this.handleRemoveClick = this.handleRemoveClick.bind(this);
        this.handleEditClick = this.handleEditClick.bind(this);
        this.handleSaveClick = this.handleSaveClick.bind(this);
      
    }

    handleRemoveClick(){
        console.log("REMOVE " )
        this.setState({edit:!this.state.edit})
        this.props.onRemoveClick()
    }

    handleSaveClick(){
        console.log("SAVE " )
        this.setState({edit:!this.state.edit})
        this.props.onSaveClick()

    }

    handleEditClick (){
        console.log("EDIT " )
        this.setState({edit:!this.state.edit})
        this.props.onEditClick()

    }

    handleCancelClick (){
        console.log("CANCEL " )
        this.setState({edit:!this.state.edit})
    }

    render() {
        return (
        <div>

        <Button hide={!this.state.edit} icon="pencil" type="warning" size="xs"  onClick={() => this.handleEditClick()}/>
        <Button hide={this.state.edit} icon="times" type="default" size="xs" onClick={() => this.handleEditClick()} />
        <Button hide={this.state.edit} icon="trash" type="danger" size="xs" onClick={() => this.handleRemoveClick()} />
        <Button hide={this.state.edit} icon="check" type="success" size="xs" onClick={() => this.handleSaveClick()}/>
        </div>
        )
    }
}