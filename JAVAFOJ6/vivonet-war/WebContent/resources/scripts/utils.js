// Ordenar options (SELECT) em ordem alfabetica
function sortOptions(options) {
    for (i=0; i<options.length-1; i++) {
        for (j=i+1; j<options.length; j++) {
            if (options[i].text > options[j].text) {
                tmpText = options[i].text;
                tmpValue = options[i].value
                options[i].text = options[j].text;
                options[i].value = options[j].value;
                options[j].text = tmpText;
                options[j].value = tmpValue;
            }
        }
    }
}
