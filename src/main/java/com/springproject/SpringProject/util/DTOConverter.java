package com.springproject.SpringProject.util;

import com.springproject.SpringProject.dto.MemberDTO;
import com.springproject.SpringProject.dto.BookDTO;
import com.springproject.SpringProject.models.Member;
import com.springproject.SpringProject.models.Book;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {

    public static MemberDTO convertToMemberDTO(Member member) {
        if (member == null) return null;
        
        return new MemberDTO(
            member.getId(),
            member.getUsername(),
            member.getEmail(),
            member.getRole(),
            member.getAddress()
        );
    }

    public static List<MemberDTO> convertToMemberDTOList(List<Member> members) {
        return members.stream()
            .map(DTOConverter::convertToMemberDTO)
            .collect(Collectors.toList());
    }

    public static BookDTO convertToBookDTO(Book book) {
        if (book == null) return null;
        
        return new BookDTO(
            book.getId(),
            book.getName(),
            book.getGenre() != null ? book.getGenre().getName() : "Unknown",
            book.getDescription(),
            book.getPrice(),
            book.getImage(),
            book.getWeight(),
            book.getQuantity()
        );
    }

    public static List<BookDTO> convertToBookDTOList(List<Book> books) {
        return books.stream()
            .map(DTOConverter::convertToBookDTO)
            .collect(Collectors.toList());
    }
}
