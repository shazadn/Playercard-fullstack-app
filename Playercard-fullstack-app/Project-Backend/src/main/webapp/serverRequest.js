function fetchData(url, method, body) {
    return new Promise((resolve, reject) => {
        const req = new XMLHttpRequest();
        req.onreadystatechange = () => {
            if (req.readyState === 4) {
                if (req.status >= 200 && req.status <= 300) {
                    resolve(req.responseText);
                } else {
                    reject("Unsuccessful");

                }
            }
        };
        req.open(method, "http://http://35.246.82.158:8080/IndividualProject-1.0/api/" + url);
        req.setRequestHeader("Content-Type", "application/json")
        req.send(body);
    });

}
