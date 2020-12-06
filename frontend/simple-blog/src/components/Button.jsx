import React, { PureComponent } from "react";

class Button extends PureComponent {
  render() {
    const { children, onPress } = this.props;
    return <button onClick={onPress}>{children}</button>;
  }
}

Button.defaultProps = {
  onPress: () => {},
};
export default Button;
