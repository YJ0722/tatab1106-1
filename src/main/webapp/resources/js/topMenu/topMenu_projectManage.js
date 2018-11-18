$(document).ready(function() {
    
    $('.overview').show();
    $('.checklist').hide();
    $('.members').hide();
    
    $('.overviewBtn').css('color', 'black').css('border-bottom', '3px solid darkorange');

    $('.overviewBtn').click(function() {
        $('.overview').show();
        $('.checklist').hide();
        $('.members').hide();
        $('.photo').show();
        $('.footer').show();
        $('.overviewBtn').css('color', 'black').css('border-bottom', '3px solid darkorange');
        $('.checklistBtn').css('color', 'black').css('border-bottom', '');
        $('.membersBtn').css('color', 'black').css('border-bottom', '');
        return false;
    });
    
    $('.checklistBtn').click(function() {
        $('.overview').hide();
        $('.checklist').show();
        $('.members').hide();
        $('.photo').hide();
        $('.footer').hide();
        $('.overviewBtn').css('color', 'black').css('border-bottom', '');
        $('.checklistBtn').css('color', 'black').css('border-bottom', '3px solid darkorange');
        $('.membersBtn').css('color', 'black').css('border-bottom', '');
        return false;
    });
        
    $('.membersBtn').click(function() {
        $('.overview').hide();
        $('.checklist').hide();
        $('.members').show();
        $('.photo').hide();
        $('.footer').hide();
        $('.overviewBtn').css('color', 'black').css('border-bottom', '');
        $('.checklistBtn').css('color', 'black').css('border-bottom', '');
        $('.membersBtn').css('color', 'black').css('border-bottom', '3px solid darkorange');
        return false;
    });
    
    
});