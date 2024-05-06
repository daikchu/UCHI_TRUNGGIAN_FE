if (typeof jQuery !== "undefined" && typeof saveAs !== "undefined") {
    (function($) {
        $.fn.wordExport = function(fileName) {
            fileName = typeof fileName !== 'undefined' ? fileName : "Hop dong cong chung";
            /*top: "Mime-Version: 1.0\nContent-Base: " + location.href + "\nContent-Type: Multipart/related; boundary=\"NEXT.ITEM-BOUNDARY\";type=\"text/html\"\n\n--NEXT.ITEM-BOUNDARY\nContent-Type: text/html; charset=\"utf-8\"\n" +
            "Content-Location: " + location.href + "\n\n<!DOCTYPE html>\n<html>\n_html_</html>",*/
            var static = {
                mhtml: {
                    top: "<html>\n_html_</html>",
                    head: "<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n<style>\n_styles_\n</style>\n</head>\n",
                    body: "<body>_body_</body>"
                }
            };
            var options = {
                maxWidth: 624
            };
            // Clone selected element before manipulating it
            var markup = $(this).clone();

            // Remove hidden elements from the output
            markup.each(function() {
                var self = $(this);
                if (self.is(':hidden'))
                    self.remove();
            });

            // Embed all images using Data URLs
            var images = Array();
            var img = markup.find('img');
            for (var i = 0; i < img.length; i++) {
                // Calculate dimensions of output image
                var w = Math.min(img[i].width, options.maxWidth);
                var h = img[i].height * (w / img[i].width);
                // Create canvas for converting image to data URL
                $('<canvas>').attr("id", "jQuery-Word-export_img_" + i).width(w).height(h).insertAfter(img[i]);
                var canvas = document.getElementById("jQuery-Word-export_img_" + i);
                canvas.width = w;
                canvas.height = h;
                // Draw image to canvas
                var context = canvas.getContext('2d');
                context.drawImage(img[i], 0, 0, w, h);
                // Get data URL encoding of image
                var uri = canvas.toDataURL();
                $(img[i]).attr("src", img[i].src);
                img[i].width = w;
                img[i].height = h;
                // Save encoded image to array
                images[i] = {
                    type: uri.substring(uri.indexOf(":") + 1, uri.indexOf(";")),
                    encoding: uri.substring(uri.indexOf(";") + 1, uri.indexOf(",")),
                    location: $(img[i]).attr("src"),
                    data: uri.substring(uri.indexOf(",") + 1)
                };
                // Remove canvas now that we no longer need it
                canvas.parentNode.removeChild(canvas);
            }

            // Prepare bottom of mhtml file with image data
            var mhtmlBottom = "\n";
            for (var i = 0; i < images.length; i++) {
                // mhtmlBottom += "--NEXT.ITEM-BOUNDARY\n";
                mhtmlBottom += "Content-Location: " + images[i].contentLocation + "\n";
                mhtmlBottom += "Content-Type: " + images[i].contentType + "\n";
                mhtmlBottom += "Content-Transfer-Encoding: " + images[i].contentEncoding + "\n\n";
                mhtmlBottom += images[i].contentData + "\n\n";
            }
            // mhtmlBottom += "--NEXT.ITEM-BOUNDARY--";

            //TODO: load css from included stylesheet
            var xmls="" ;
               /* "<!--[if gte mso 9]><xml>\n" +
                " <w:WordDocument>\n" +
                "  <w:View>Print</w:View>\n" +
                "  <w:ValidateAgainstSchemas/>\n" +
                "  <w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid>\n" +
                "  <w:IgnoreMixedContent>false</w:IgnoreMixedContent>\n" +
                "  <w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText>\n" +
                "  <w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel>\n" +
                " </w:WordDocument>\n" +
                "</xml><![endif]-->";*/
            var styles = "<!--\n" +
                " @page\n" +
                "\t{mso-footnote-separator:url(\"paging_files/header.htm\") fs;\n" +
                "\tmso-footnote-continuation-separator:url(\"paging_files/header.htm\") fcs;\n" +
                "\tmso-endnote-separator:url(\"paging_files/header.htm\") es;\n" +
                "\tmso-endnote-continuation-separator:url(\"paging_files/header.htm\") ecs;}\n" +
                " /* Style Definitions */\n" +
                "@page Section1\n" +
                "\t{size:21cm 29.5cm;\n" +
                "\tmargin:2cm 1.5cm 2cm 2.5cm;\n" +
                "\tmso-header-margin:.5in;\n" +
                "\tmso-footer-margin:.5in;\n" +
                "\tmso-even-footer:url(\"paging_files/header.htm\") ef1;\n" +
                "\tmso-footer:url(\"paging_files/header.htm\") f1;\n" +
                "\tmso-paper-source:0;}\n" +
                "div.Section1\n" +
                "\t{page:Section1;}\n" +
                "-->" +

                ".break { page-break-before: always; }";

            // Aggregate parts of the file together 
            var fileContent = static.mhtml.top.replace("_html_", static.mhtml.head.replace("_styles_", styles) + static.mhtml.body.replace("_body_", markup.html())) + mhtmlBottom;
console.log(fileContent);
            fileContent=fileContent.replace("<style>",xmls+"<style>");
            fileContent=fileContent.replace("<body>","<body><div class='Section1'>");

           // fileContent=fileContent.replace(new RegExp("line-height: 150%;", 'g'),"margin-top:6.0pt;margin-right:0pt;margin-bottom:6.0pt;margin-left:0pt;text-indent:1cm;line-height: 150%;");
            fileContent=fileContent.replace("</body>","</div></body>");
            console.log(fileContent);
            // Create a Blob with the file contents
            var blob = new Blob([fileContent], { type: "application/msword;charset=utf-8"});

          //  var blob =htmlDocx.asBlob(fileContent, {orientation: 'portrait', margins: {top: 1133,right:850,bottom:1133,left:1400}});
            saveAs(blob, fileName + ".doc");
        };
    })(jQuery);
} else {
    if (typeof jQuery === "undefined") {
        console.error("jQuery Word Export: missing dependency (jQuery)");
    }
    if (typeof saveAs === "undefined") {
        console.error("jQuery Word Export: missing dependency (FileSaver.js)");
    };
}
