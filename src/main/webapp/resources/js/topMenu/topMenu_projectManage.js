$(document).ready(function() {
    
    $('.overview').show();
    $('.checklist').hide();
    $('.members').hide();
    
    $('.overviewBtn').css('color', 'aliceblue').css('text-decoration', 'underline');

    $('.overviewBtn').click(function() {
        $('.overview').show();
        $('.checklist').hide();
        $('.members').hide();
        $('.photo').show();
        $('.footer').show();
        $('.overviewBtn').css('color', 'aliceblue').css('text-decoration', 'underline');
        $('.checklistBtn').css('color', 'black').css('text-decoration', 'none');
        $('.membersBtn').css('color', 'black').css('text-decoration', 'none');
        return false;
    });
    
    $('.checklistBtn').click(function() {
        $('.overview').hide();
        $('.checklist').show();
        $('.members').hide();
        $('.photo').hide();
        $('.footer').hide();
        $('.overviewBtn').css('color', 'black').css('text-decoration', 'none');
        $('.checklistBtn').css('color', 'aliceblue').css('text-decoration', 'underline');
        $('.membersBtn').css('color', 'black').css('text-decoration', 'none');
        return false;
    });
        
    $('.membersBtn').click(function() {
        $('.overview').hide();
        $('.checklist').hide();
        $('.members').show();
        $('.photo').hide();
        $('.footer').hide();
        $('.overviewBtn').css('color', 'black').css('text-decoration', 'none');
        $('.checklistBtn').css('color', 'black').css('text-decoration', 'none');
        $('.membersBtn').css('color', 'aliceblue').css('text-decoration', 'underline');
        return false;
    });
    
    
});