let wStorage = window.localStorage;
let personDetails = wStorage.getItem("personDetails");

function createHTML(htmlStr) {
  var tempContainer = document.createElement('div');
  htmlStr = htmlStr.trim();
  tempContainer.innerHTML = htmlStr;
  var htmlNode = tempContainer.firstChild;
  console.log(htmlNode);
  
  return htmlNode;
}

function generateElementNode(elementId,data) {
  let domElementStr,domElement;
  switch (elementId) {
    case "friends-list": domElementStr = `<li class="col-6 col-sm-3 col-lg-2 mr-0 list-inline-item">
                                            <img src="" alt="" class="img-75" data-desc="image">
                                            <p id="text class="text-justify" data-desc="text"></p>
                                          </li>`;
                        domElement = createHTML(domElementStr);
                        domElement.querySelectorAll("[data-desc = image]")[0].src = data.imageURL;
                        // "https://picsum.photos/200?random";
                        domElement.querySelectorAll("[data-desc = text]")[0].innerHTML = data.name;
                        break;
    case "likes-list": domElementStr = `<li class="col-6 col-sm-3 col-lg-2 mr-0 list-inline-item">
                                          <img src="" alt="" class="img-75" data-desc="image">
                                          <p id="text class="text-justify" data-desc="text"></p>
                                        </li>`;
                      domElement = createHTML(domElementStr);
                      domElement.querySelectorAll("[data-desc = image]")[0].src = data.imageURL;
                      // "https://picsum.photos/200?random";
                      domElement.querySelectorAll("[data-desc = text]")[0].innerHTML = data.name;
                      break;
    case "interests-list": domElementStr = `<li class="col-6 col-sm-3 col-lg-2 mr-0 list-inline-item">
                                              <img src="" alt="" class="img-75" data-desc="image">
                                              <p id="text class="text-justify" data-desc="text"></p>
                                            </li>`;
                          domElement = createHTML(domElementStr);
                          domElement.querySelectorAll("[data-desc = image]")[0].src = data.imageURL;
                          // "https://picsum.photos/200?random";
                          domElement.querySelectorAll("[data-desc = text]")[0].innerHTML = data.name;
                          break;
default:
    break;
  }
  return domElement;
}

function populateDataInElement(elementId,data,isLink=false) {
  console.log(isLink);
  
  if (Array.isArray(data)) {
    data.forEach(function (dataInstance) {
      let domElement = generateElementNode(elementId,dataInstance)
      document.getElementById(elementId).appendChild(domElement);  
    });
  }
  else if(isLink === true) {
      if (elementId === "dp-self") {
        document.getElementById(elementId).src = data;        
      }
      else{
        document.getElementById(elementId).href = data;
      }
      return;
    }
  else{
    document.getElementById(elementId).innerHTML=data;
  }
}

if (personDetails) {
  personDetails = JSON.parse(personDetails);
  populateDataInElement("dp-self", "https://picsum.photos/100?random", true);
  populateDataInElement("name-self", personDetails.personalDetails.name);
  populateDataInElement("dob-self", personDetails.personalDetails.dob);
  populateDataInElement("city-self", personDetails.personalDetails.city);
  populateDataInElement("company-self", personDetails.personalDetails.company);
  populateDataInElement("facebook-self", personDetails.personalDetails.facebookURL,true);
  populateDataInElement("twitter-self", personDetails.personalDetails.twitterURL,true);
  populateDataInElement("github-self", personDetails.personalDetails.githubURL,true);
  populateDataInElement("linkedin-self", personDetails.personalDetails.linkedinURL,true);
  populateDataInElement("bio-text", personDetails.bioDetails);
  populateDataInElement("friends-list", personDetails.friendDetails);
  populateDataInElement("likes-list", personDetails.likeDetails);
  populateDataInElement("interests-list", personDetails.interestDetails);
}
else{
  alert("User details doesn't exist in records. Please fill the details first.");
  window.open("/edit.html","_self");
}

document.getElementById("delete-user").addEventListener("click",function () {
  wStorage.removeItem("personDetails");
  alert("User deleted. Please fill the details again.");
  window.open("/edit.html","_self");
})