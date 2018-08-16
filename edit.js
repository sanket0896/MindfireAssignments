// initialise session storage or local storage

let wStorage = window.localStorage;

let personDetails = {
    "personalDetails" : {
        "dpURLSelf" : "",
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
            if (data!=="") {
                parentNode.src = data;                
            }
        }
    }
    else{
        parentNode.value=data;
    }
}

function reloadElements(whatToUpdate) {    
    let currentPerson = getDataFromStorage("personDetails");
    console.log("djnsjd");
    
    if (currentPerson) {
        switch (whatToUpdate) {
            case "selfDP":
                reloadSelfDPElements(currentPerson.personalDetails.dpURLSelf);
                break;
            case "personalDetails":
                reloadPersonalDetailsElements(currentPerson.personalDetails);
                break;
            case "bio":
                reloadBioElements(currentPerson.bioDetails);
                break;
            case "friends":
                reloadFriendsElements(currentPerson.friendDetails);
                break;
            case "likes":
                reloadLikesElements(currentPerson.likeDetails);
                break;
            case "interests":
                reloadInterestsElements(currentPerson.interestDetails);
                break;
            case "all":
                console.log("all");
                
                reloadSelfDPElements(currentPerson.personalDetails.dpURLSelf);
                reloadPersonalDetailsElements(currentPerson.personalDetails);
                reloadBioElements(currentPerson.bioDetails);
                reloadFriendsElements(currentPerson.friendDetails);
                reloadLikesElements(currentPerson.likeDetails);
                reloadInterestsElements(currentPerson.interestDetails);
                break;
            default:
                break;
        }
    }
}

function reloadSelfDPElements(data) {
    populateDataInElement("dp-self", data, true);    
}
function reloadPersonalDetailsElements(data) {
    populateDataInElement("name-self", data.name);
    populateDataInElement("dob-self", data.dob);
    populateDataInElement("city-self", data.city);
    populateDataInElement("company-self", data.company);
    populateDataInElement("facebook-self", data.facebookURL);
    populateDataInElement("twitter-self", data.twitterURL);
    populateDataInElement("github-self", data.githubURL);
    populateDataInElement("linkedin-self", data.linkedinURL);
}
function reloadBioElements(data) {
    populateDataInElement("bio-text", data);
}
function reloadFriendsElements(data) {
    populateDataInElement("friends-list", data);
}
function reloadLikesElements(data) {
    populateDataInElement("likes-list", data);
}
function reloadInterestsElements(data) {
    populateDataInElement("interests-list", data);
}

//initial page load
reloadElements("all");

$("#modal-form-dp").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    currentPerson.personalDetails.dpURLSelf = e.target.elements[0].value;
    // e.target.elements[0].value = "";
    $("#dp-upload-modal").modal('hide');
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    reloadElements("selfDP");
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
    reloadElements("personalDetails");
    return false;
});

$("#page-form-bio").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    currentPerson.bioDetails = e.target.elements[0].value;
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    reloadElements("bio");
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
    reloadElements("friends");
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
    reloadElements("likes");
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
    reloadElements("interests");
    return false;
});

$("#delete-user").on("click",function () {
    wStorage.removeItem("personDetails");
    alert("User deleted. Please fill the details again.");
    window.open("./edit.html","_self");
});

