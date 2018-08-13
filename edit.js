// initialise session storage or local storage

let wStorage = window.localStorage;

let personDetails = {
    "personalDetails" : {
        "dpURLSelf" : "#",
        "name": "",
        "dob": "",
        "city": "",
        "company": "",
        "facebookURL": "",
        "twitterURL": "",
        "githubURL": "",
        "linkedinURL": ""
    },
    "bioDetails" : "",
    "friendDetails" : [],
    "likeDetails" : [],
    "interestDetails" : []
};

function getDataFromStorage(key) {
    if (wStorage.getItem(key)) {
        value = JSON.parse(wStorage.getItem(key));
        return value;
    }
    else{
        return personDetails;
    }
}

function populateDataInElement(elementId,data,isLink=false) {
    let parentNode = document.getElementById(elementId); 
    if (Array.isArray(data)) {
        console.log(parentNode.childNodes);
        
        while (parentNode.childNodes.length>2) {
            parentNode.removeChild(parentNode.lastChild);
        }
        data.forEach(function (dataInstance) {
            let domElement = generateElementNode(elementId,dataInstance)
            parentNode.appendChild(domElement);  
        });
    }
    else if(isLink === true) {
        if (elementId === "dp-self") {
            if (data!="#") {
                parentNode.src = data;                
            }
        }
        else{
            parentNode.href = data;
        }
        return;
    }
    else{
        parentNode.value=data;
    }
}

function reloadElements() {
    let currentPerson = getDataFromStorage("personDetails");
    if (currentPerson) {
        populateDataInElement("dp-self", currentPerson.personalDetails.dpURLSelf, true);
        populateDataInElement("name-self", currentPerson.personalDetails.name);
        populateDataInElement("dob-self", currentPerson.personalDetails.dob);
        populateDataInElement("city-self", currentPerson.personalDetails.city);
        populateDataInElement("company-self", currentPerson.personalDetails.company);
        populateDataInElement("facebook-self", currentPerson.personalDetails.facebookURL,true);
        populateDataInElement("twitter-self", currentPerson.personalDetails.twitterURL,true);
        populateDataInElement("github-self", currentPerson.personalDetails.githubURL,true);
        populateDataInElement("linkedin-self", currentPerson.personalDetails.linkedinURL,true);
        populateDataInElement("bio-text", currentPerson.bioDetails);
        populateDataInElement("friends-list", currentPerson.friendDetails);
        populateDataInElement("likes-list", currentPerson.likeDetails);
        populateDataInElement("interests-list", currentPerson.interestDetails);
    }
}

reloadElements();

$("#modal-form-dp").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    currentPerson.personalDetails.dpURLSelf = e.target.elements[0].value;
    // e.target.elements[0].value = "";
    $("#dp-upload-modal").modal('hide');
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    reloadElements();
    return false;
});

$("#page-form-personal-details").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    currentPerson.personalDetails.name = e.target.elements[0].value;
    // e.target.elements[0].value = "";
    currentPerson.personalDetails.dob = e.target.elements[1].value;
    // e.target.elements[1].value = "";
    currentPerson.personalDetails.city = e.target.elements[2].value;
    // e.target.elements[2].value = "";
    currentPerson.personalDetails.company = e.target.elements[3].value;
    // e.target.elements[3].value = "";
    currentPerson.personalDetails.facebookURL = e.target.elements[4].value;
    // e.target.elements[4].value = "";
    currentPerson.personalDetails.twitterURL = e.target.elements[5].value;
    // e.target.elements[5].value = "";
    currentPerson.personalDetails.githubURL = e.target.elements[6].value;
    // e.target.elements[6].value = "";
    currentPerson.personalDetails.linkedinURL = e.target.elements[7].value;
    // e.target.elements[7].value = "";
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    reloadElements();
    return false;
});

$("#page-form-bio").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    currentPerson.bioDetails = e.target.elements[0].value;
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    reloadElements();
    return false;
})

$("#modal-form-friend").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    let currentFriend = {
        "imageURL": e.target.elements[0].value,
        "name": e.target.elements[1].value
    }
    currentPerson.friendDetails.push(currentFriend);
    e.target.elements[0].value = "";
    e.target.elements[1].value = "";
    $("#friend-upload-modal").modal('hide');
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    reloadElements();
    return false;
});

$("#modal-form-like").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    let currentLike = {
        "imageURL": e.target.elements[0].value,
        "name": e.target.elements[1].value
    }
    currentPerson.likeDetails.push(currentLike);
    e.target.elements[0].value = "";
    e.target.elements[1].value = "";
    $("#like-upload-modal").modal('hide');
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    reloadElements();
    return false;
});

$("#modal-form-interest").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    let currentInterest = {
        "imageURL": e.target.elements[0].value,
        "name": e.target.elements[1].value
    }
    currentPerson.interestDetails.push(currentInterest);
    e.target.elements[0].value = "";
    e.target.elements[1].value = "";
    $("#interest-upload-modal").modal('hide');
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));    
    reloadElements();
    return false;
});

$("#delete-user").on("click",function () {
    wStorage.removeItem("personDetails");
    alert("User deleted. Please fill the details again.");
    window.open("/edit.html","_self");
});

