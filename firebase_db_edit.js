var database = firebase.database();
var storage = firebase.storage();
var storageRef = storage.ref();
function uploadFile(file,path){
    var filename = file.name;
    var uploadTask = storageRef.child(path+'/'+filename).put(file);
    console.log(filename+"  "+path);
    
}