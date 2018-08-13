// initialise session storage or local storage

let wStorage = window.sessionStorage;

//on form submit, create JSON object and write JSON to session

document.getElementById("modal-form-dp").addEventListener("submit", (e)=>{
    e.preventDefault();
    
    let inp = e.target.elements;
    for (let i = 0; i < inp.length; i++) {
        console.log(inp[i]);
                
    }
    return false;
});