import { Example } from 'aep-plugin';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    Example.echo({ value: inputValue })
}
