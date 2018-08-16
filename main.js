let wStorage = window.localStorage;
let personDetails = wStorage.getItem("personDetails");

function populateDataInElement(elementId,data,isLink=false,isSocial=false) {
  let parentNode = document.getElementById(elementId);
  if (Array.isArray(data)) {
    data.forEach(function (dataInstance) {
      let domElement = generateElementNode(elementId,dataInstance)
      parentNode.appendChild(domElement);  
    });
  }
  else if(isLink === true) {
      if (elementId === "dp-self") {
        if (data !== "") {
          parentNode.src = data;          
        }
      }
      else if(isSocial === true){
        if (data === "") {
          parentNode.parentElement.removeChild(parentNode);
        }
        let socialData;
        switch (elementId) {
          case "facebook-self":
            socialData = "https://www.facebook.com/"+data;
            break;
          case "twitter-self":
            socialData = "https://twitter.com/"+data;
            break;
          case "github-self":
            socialData = "https://github.com/"+data;
            break;
          case "linkedin-self":
            socialData = "https://www.linkedin.com/in/"+data;
            break;
          default:

            break;
        }
        parentNode.href = socialData;
      }
      return;
    }
  else{
    if (elementId === "dob-self") {     
      parentNode.innerHTML=data.split("-").reverse().join("-");
    }
    else{
      parentNode.innerHTML=data;
    }
  }
}

if (personDetails) {
  personDetails = JSON.parse(personDetails);
  populateDataInElement("dp-self", personDetails.personalDetails.dpURLSelf, true);
  populateDataInElement("name-self", personDetails.personalDetails.name);
  populateDataInElement("dob-self", personDetails.personalDetails.dob);
  populateDataInElement("city-self", personDetails.personalDetails.city);
  populateDataInElement("company-self", personDetails.personalDetails.company);
  populateDataInElement("facebook-self", personDetails.personalDetails.facebookURL,true,true,true);
  populateDataInElement("twitter-self", personDetails.personalDetails.twitterURL,true,true);
  populateDataInElement("github-self", personDetails.personalDetails.githubURL,true,true);
  populateDataInElement("linkedin-self", personDetails.personalDetails.linkedinURL,true,true);
  populateDataInElement("bio-text", personDetails.bioDetails);
  populateDataInElement("friends-list", personDetails.friendDetails);
  populateDataInElement("likes-list", personDetails.likeDetails);
  populateDataInElement("interests-list", personDetails.interestDetails);
}
else{
  alert("User details doesn't exist in records. Please fill the details first.");
  window.open("./edit.html","_self");
}

document.getElementById("delete-user").addEventListener("click",function () {
  wStorage.removeItem("personDetails");
  alert("User deleted. Please fill the details again.");
  window.open("./edit.html","_self");
})